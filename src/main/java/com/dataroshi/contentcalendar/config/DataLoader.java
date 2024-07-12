package com.dataroshi.contentcalendar.config;

import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.repository.ContentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
// should not be config package but....
@Profile("!production")
@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    private final ContentRepository contentRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository contentRepository, ObjectMapper objectMapper) {
        this.contentRepository = contentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(contentRepository.count() != 0) {
            LOG.info("There are contents in this database");
            return;
        }
        try(InputStream inputStream = new ClassPathResource("data/content.json").getInputStream()) {
            LOG.info("Loading content...");
            var contents = objectMapper.readValue(inputStream, new TypeReference<List<Content>>() {});
            contentRepository.saveAll(contents);
        } catch (IOException e) {
            LOG.error("error loading data", e);
        }
    }
}
