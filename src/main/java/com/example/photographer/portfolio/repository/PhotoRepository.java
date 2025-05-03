package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findBySessionIdAndFilename(Long sessionId, String filename);
}
