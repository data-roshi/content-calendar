package com.dataroshi.contentcalendar.repository;

import com.dataroshi.contentcalendar.model.Content;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {

    List<Content> findAllByTitleContainsIgnoreCase(String keyword);

    @Query("SELECT * FROM Content WHERE status = :status")
    List<Content> findAllByStatus(@Param("status") String status);
}
