package com.dataroshi.contentcalendar.controller;

import com.dataroshi.contentcalendar.config.ContentCalendarConfigProperties;
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

    private final ContentCalendarConfigProperties properties;

    public HomeController(ContentCalendarConfigProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/value")
    public String home() {
        return welcomeMessage;
    }

    @GetMapping("/more-values")
    public Map<String, String> more() {
        return Map.of(
                "defaultMessage", defaultMessage,
                "anotherMessage", anotherMessage);
    }

    @GetMapping("/config-props")
    public Map<String, String> configProperties() {
        return Map.of(
                "city1", properties.city1(),
                "city2", properties.city2());
    }
}
