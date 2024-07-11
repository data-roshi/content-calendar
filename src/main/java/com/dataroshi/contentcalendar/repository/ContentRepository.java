package com.dataroshi.contentcalendar.repository;

import com.dataroshi.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
}
