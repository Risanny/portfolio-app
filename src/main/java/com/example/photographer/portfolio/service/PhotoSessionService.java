package com.example.photographer.portfolio.service;

import com.example.photographer.portfolio.model.PhotoSession;

import java.util.List;
import java.util.Optional;

public interface PhotoSessionService {
    List<PhotoSession> listAll();
    Optional<PhotoSession> getById(Long id);
    PhotoSession create(PhotoSession session);
    PhotoSession update(Long id, PhotoSession session);
    void delete(Long id);
}

