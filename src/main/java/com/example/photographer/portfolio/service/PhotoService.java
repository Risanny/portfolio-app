package com.example.photographer.portfolio.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    /**
     * Сохраняет переданные файлы в директорию uploads/{sessionId} и в БД создает сущности Photo
     */
    void uploadPhotos(Long sessionId, MultipartFile[] files);
    void deletePhoto(Long photoId);
    void deletePhotoByFilename(Long sessionId, String filename);
}