package com.dataroshi.contentcalendar.repository;

import com.dataroshi.contentcalendar.model.Content;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> content = new ArrayList<>();

    public List<Content> findAll() {
        return content;
    }

    public Optional<Content> findById(Integer id) {
       return content.stream()
               .filter(Objects::nonNull)
               .filter(c -> c.id().equals(id))
               .findAny();
    }
}
