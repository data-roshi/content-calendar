package com.dataroshi.contentcalendar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "CONTENT")
public record Content(
        @Id
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
