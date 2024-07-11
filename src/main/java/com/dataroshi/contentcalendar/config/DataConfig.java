package com.dataroshi.contentcalendar.config;

import com.dataroshi.contentcalendar.model.Content;
import com.dataroshi.contentcalendar.model.Status;
import com.dataroshi.contentcalendar.model.Type;
import com.dataroshi.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataConfig {

    // probably should be somewhere else is not a config.
    @Bean
    public CommandLineRunner commandLineRunner(ContentRepository contentRepository) {
        return args -> {
            var content = new Content(null,
                    "Blog Post 1",
                    "Blog post 1",
                    Status.IDEA,
                    Type.ARTICLE,
                    LocalDateTime.now(),
                    null,
                    "");

            contentRepository.save(content);
        };
    }
}
