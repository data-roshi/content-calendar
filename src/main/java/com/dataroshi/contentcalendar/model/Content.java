package com.dataroshi.contentcalendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record Content(
        Integer id,
        @NotBlank(message = "title element cannot be blank")
        String title,
        String desc,
        @NotNull
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
