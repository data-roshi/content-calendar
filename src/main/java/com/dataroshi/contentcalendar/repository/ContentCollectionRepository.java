package com.dataroshi.contentcalendar.repository;

import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.model.Status;
import com.dataroshi.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contents = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    public List<Content> findAll() {
        return contents;
    }

    public Optional<Content> findById(Integer id) {
        return contents.stream()
                .filter(c -> c.id() != null)
                .filter(c -> c.id().equals(id))
                .findAny();
    }

    public void save(Content content) {
        if (content.id() != null) {
            contents.removeIf(c -> c.id().equals(content.id()));
            contents.add(content);
        } else {
            contents.add(new Content(content, counter.incrementAndGet()));
        }
    }

    public boolean existsById(Integer id) {
        return contents.stream()
                .filter(c -> c.id() != null)
                .anyMatch(c -> c.id().equals(id));
    }

    public void deleteById(Integer id) {
        contents.removeIf(c -> c.id().equals(id));
    }

    @PostConstruct
    private void onInit() {
        var content = new Content(counter.incrementAndGet(),
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
