package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    // TODO: при необходимости добавить методы поиска по названию или дате,
// например List<PhotoSession> findByTypeName(String typeName);

}
