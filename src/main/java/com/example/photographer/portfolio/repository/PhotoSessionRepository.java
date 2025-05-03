package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Long> {
    /** Сколько сессий ссылаются на данный тип */
    long countByTypeId(Long typeId);
}
