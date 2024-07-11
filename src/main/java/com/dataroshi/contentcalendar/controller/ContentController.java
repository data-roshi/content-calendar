package com.dataroshi.contentcalendar.controller;

import com.dataroshi.contentcalendar.exception.ContentNotFoundException;
import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        LOG.info("Find all contents");
        return contentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        LOG.info("Find content by id: {}", id);
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Content with id %s not found", id)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        LOG.info("Creating new content {}", content);
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        LOG.info("Updating content with id {}", id);
        if (!contentRepository.existsById(id)) {
            throw new ContentNotFoundException(String.format("Content with id %s not found", id));
        }

        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        LOG.info("Deleting content with id {}", id);
        contentRepository.deleteById(id);
    }

    @GetMapping("/filter/title/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword) {
        LOG.info("Find content by title: {}", keyword);
        return contentRepository.findAllByTitleContainsIgnoreCase(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable String status) {
        LOG.info("Find content by status: {}", status);
        return contentRepository.findAllByStatus(status);
    }
}
