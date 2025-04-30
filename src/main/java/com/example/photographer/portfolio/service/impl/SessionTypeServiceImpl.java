package com.example.photographer.portfolio.service.impl;

import com.example.photographer.portfolio.model.SessionType;
import com.example.photographer.portfolio.repository.SessionTypeRepository;
import com.example.photographer.portfolio.service.SessionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionTypeServiceImpl implements SessionTypeService {

    private final SessionTypeRepository repo;

    public SessionTypeServiceImpl(SessionTypeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<SessionType> listAll() {
        return repo.findAll();
    }

    @Override
    public Optional<SessionType> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public SessionType create(SessionType sessionType) {
        return repo.save(sessionType);
    }

    @Override
    public SessionType update(Long id, SessionType sessionType) {
        sessionType.setId(id);
        return repo.save(sessionType);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
