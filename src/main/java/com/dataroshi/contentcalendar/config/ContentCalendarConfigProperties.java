package com.dataroshi.contentcalendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cc")
public record ContentCalendarConfigProperties(String city1, String city2) {
}
