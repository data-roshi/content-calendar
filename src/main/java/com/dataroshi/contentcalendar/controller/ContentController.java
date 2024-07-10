package com.dataroshi.contentcalendar.controller;

import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {



    private final ContentCollectionRepository contentCollectionRepository;

    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentCollectionRepository = contentCollectionRepository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return contentCollectionRepository.findAll();
    }
}
