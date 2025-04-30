package com.example.photographer.portfolio.service;

import com.example.photographer.portfolio.model.SessionType;
import java.util.List;
import java.util.Optional;

/**
 * Бизнес-логика для управления типами фотосессий.
 */
public interface SessionTypeService {
    List<SessionType> listAll();
    Optional<SessionType> getById(Long id);
    SessionType create(SessionType sessionType);
    SessionType update(Long id, SessionType sessionType);
    void delete(Long id);
}
