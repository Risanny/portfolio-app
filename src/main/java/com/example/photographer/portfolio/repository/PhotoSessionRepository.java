package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Long> {
    // TODO: при необходимости добавить методы поиска по названию или дате,
// например List<PhotoSession> findByTypeName(String typeName);

}
