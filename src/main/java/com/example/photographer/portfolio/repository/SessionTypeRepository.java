package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionTypeRepository extends JpaRepository<SessionType, Long> {
    // TODO: при необходимости добавить методы поиска по названию или дате,
// например List<PhotoSession> findByTypeName(String typeName);

}
