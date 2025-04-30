package com.example.photographer.portfolio.service.impl;

import com.example.photographer.portfolio.model.Photo;
import com.example.photographer.portfolio.model.PhotoSession;
import com.example.photographer.portfolio.repository.PhotoRepository;
import com.example.photographer.portfolio.repository.PhotoSessionRepository;
import com.example.photographer.portfolio.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepo;
    private final PhotoSessionRepository sessionRepo;

    // Директория для сохранения файлов (можно настроить в application.properties)
    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    public PhotoServiceImpl(PhotoRepository photoRepo,
                            PhotoSessionRepository sessionRepo) {
        this.photoRepo = photoRepo;
        this.sessionRepo = sessionRepo;
    }

    @Override
    public void uploadPhotos(Long sessionId, MultipartFile[] files) {
        PhotoSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Session not found: id=" + sessionId
                ));

        Path sessionFolder = Paths.get(uploadDir, sessionId.toString());
        try {
            Files.createDirectories(sessionFolder);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Cannot create directory for uploads", e
            );
        }

        for (MultipartFile file : files) {
            String original = file.getOriginalFilename();
            String ext = original != null && original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
            String filename = UUID.randomUUID() + ext;
            Path target = sessionFolder.resolve(filename);
            try {
                file.transferTo(target);
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Failed to save file: " + original, e
                );
            }
            // Сохраняем запись в БД
            Photo photo = new Photo();
            photo.setFilename(filename);
            photo.setSession(session);
            photoRepo.save(photo);
        }
    }
}