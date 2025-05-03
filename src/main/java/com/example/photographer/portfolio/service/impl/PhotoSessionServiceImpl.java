package com.example.photographer.portfolio.service.impl;

import com.example.photographer.portfolio.model.PhotoSession;
import com.example.photographer.portfolio.repository.PhotoSessionRepository;
import com.example.photographer.portfolio.service.PhotoSessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoSessionServiceImpl implements PhotoSessionService {

    private final PhotoSessionRepository repo;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

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
        // 1) Удаляем все файлы сессии из uploads/{id}/...
        Path sessionFolder = Paths.get(uploadDir, String.valueOf(id));
        try {
            if (Files.exists(sessionFolder)) {
                Files.walk(sessionFolder)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete session files: " + sessionFolder, e
            );
        }

        // 2) Удаляем саму сессию (и связанные PHOTO через cascade)
        repo.deleteById(id);
    }
}

