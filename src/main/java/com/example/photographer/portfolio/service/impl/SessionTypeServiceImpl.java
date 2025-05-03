package com.example.photographer.portfolio.service.impl;

import com.example.photographer.portfolio.model.SessionType;
import com.example.photographer.portfolio.repository.SessionTypeRepository;
import com.example.photographer.portfolio.repository.PhotoSessionRepository;
import com.example.photographer.portfolio.service.SessionTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SessionTypeServiceImpl implements SessionTypeService {

    private final SessionTypeRepository     repo;
    private final PhotoSessionRepository sessionRepo;

    public SessionTypeServiceImpl(SessionTypeRepository repo,
                                  PhotoSessionRepository sessionRepo) {
        this.repo        = repo;
        this.sessionRepo = sessionRepo;
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
        // Предварительная проверка: есть ли сессии этого типа?
        long count = sessionRepo.countByTypeId(id);
        if (count > 0) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Нельзя удалить тип: существуют " + count + " привязанных фотосессий"
            );
        }
        repo.deleteById(id);
    }
}
