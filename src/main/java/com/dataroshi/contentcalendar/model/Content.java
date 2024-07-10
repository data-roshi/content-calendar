package com.dataroshi.contentcalendar.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Content(
        Integer id,
        String title,
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
    public Content(Content content, Integer id) {
        this(
                id,
                content.title,
                content.desc, content.status,
                content.contentType,
                content.dateCreated,
                content.dateUpdated,
                content.url);
    }
}
