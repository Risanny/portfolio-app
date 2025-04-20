package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // TODO: при необходимости добавить методы поиска по названию или дате,
// например List<PhotoSession> findByTypeName(String typeName);

}
