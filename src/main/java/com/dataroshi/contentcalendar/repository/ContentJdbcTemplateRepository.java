package com.dataroshi.contentcalendar.repository;

import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.model.Status;
import com.dataroshi.contentcalendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated",LocalDateTime.class),
                rs.getString("url"));
    }

    public List<Content> findAll() {
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
    }

    public void create(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL);
    }

    public void update(int id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Content find(int id) {
        String sql = "SELECT * FROM Content WHERE id=?";
        return jdbcTemplate.queryForObject(sql, ContentJdbcTemplateRepository::mapRow, id);
    }
}
