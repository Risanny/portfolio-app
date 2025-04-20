package com.example.photographer.portfolio.service.impl;

import com.example.photographer.portfolio.model.PhotoSession;
import com.example.photographer.portfolio.repository.PhotoSessionRepository;
import com.example.photographer.portfolio.service.PhotoSessionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoSessionServiceImpl implements PhotoSessionService {

    private final PhotoSessionRepository repo;

    public PhotoSessionServiceImpl(PhotoSessionRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<PhotoSession> listAll() {
        return repo.findAll();
    }

    @Override
    public Optional<PhotoSession> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public PhotoSession create(PhotoSession session) {
        // TODO: здесь можно валидировать дату/название
        return repo.save(session);
    }

    @Override
    public PhotoSession update(Long id, PhotoSession session) {
        session.setId(id);
        return repo.save(session);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

