package com.dataroshi.contentcalendar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @Value("${cc.welcome1}")
    private String welcomeMessage;
    @Value("${cc.welcome2: Default Welcome msg}")
    private String defaultMessage;
    @Value("${cc.welcome3: Overwrite this default msg}")
    private String anotherMessage;

    @GetMapping("/")
    public String home() {
        return welcomeMessage;
    }

    public Map<String, String> more() {
        return Map.of(
                "defaultMessage", defaultMessage,
                "anotherMessage", anotherMessage);
    }
}
