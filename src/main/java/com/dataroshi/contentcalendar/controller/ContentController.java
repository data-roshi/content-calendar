package com.dataroshi.contentcalendar.controller;

import com.dataroshi.contentcalendar.exception.ContentNotFoundException;
import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.repository.ContentCollectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {


    private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

    private final ContentCollectionRepository contentCollectionRepository;

    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentCollectionRepository = contentCollectionRepository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        LOG.info("Find all contents");
        return contentCollectionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        LOG.info("Find content by id: {}", id);
        return contentCollectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Content with id %s not found", id)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) {
        LOG.info("Creating new content {}", content);
        contentCollectionRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        LOG.info("Updating content with id {}", id);
        if (!contentCollectionRepository.existsById(id)) {
            throw new ContentNotFoundException(String.format("Content with id %s not found", id));
        }

        contentCollectionRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        contentCollectionRepository.deleteById(id);
    }
}
