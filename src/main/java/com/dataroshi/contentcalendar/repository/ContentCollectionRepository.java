package com.dataroshi.contentcalendar.repository;

import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.model.Status;
import com.dataroshi.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contents = new ArrayList<>();

    public List<Content> findAll() {
        return contents;
    }

    public Optional<Content> findById(Integer id) {
        return contents.stream()
                .filter(Objects::nonNull)
                .filter(c -> c.id().equals(id))
                .findAny();
    }

    @PostConstruct
    private void onInit() {
        var content = new Content(1,
                "Blog Post 1",
                "Blog post 1",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");

        contents.add(content);
    }
}
