package com.example.photographer.portfolio.controller;

import com.example.photographer.portfolio.dto.PhotoSessionDto;
import com.example.photographer.portfolio.model.PhotoSession;
import com.example.photographer.portfolio.model.Photo;
import com.example.photographer.portfolio.service.PhotoSessionService;
import com.example.photographer.portfolio.service.SessionTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.photographer.portfolio.service.PhotoService;
import org.springframework.web.multipart.MultipartFile;


import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessions")
public class PhotoSessionController {

    private final PhotoSessionService sessionService;
    private final SessionTypeService typeService;
    private final PhotoService photoService;

    public PhotoSessionController(PhotoSessionService sessionService,
                                  SessionTypeService typeService,
                                  PhotoService photoService) {
        this.sessionService = sessionService;
        this.typeService = typeService;
        this.photoService = photoService;
    }

    /** GET all */
    @GetMapping
    public List<PhotoSessionDto> listAll() {
        return sessionService.listAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /** GET by ID */
    @GetMapping("/{id}")
    public ResponseEntity<PhotoSessionDto> getById(@PathVariable Long id) {
        return sessionService.getById(id)
                .map(ps -> ResponseEntity.ok(toDto(ps)))
                .orElse(ResponseEntity.notFound().build());
    }

    /** POST create new */
    @PostMapping
    public ResponseEntity<PhotoSessionDto> create(@RequestBody PhotoSessionDto dto) {
        // Находим тип
        var type = typeService.getById(dto.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid typeId"));
        // Заполняем сущность
        PhotoSession ps = new PhotoSession();
        ps.setTitle(dto.getTitle());
        ps.setDescription(dto.getDescription());
        ps.setSessionDate(dto.getSessionDate());
        ps.setType(type);
        PhotoSession created = sessionService.create(ps);
        PhotoSessionDto result = toDto(created);
        return ResponseEntity
                .created(URI.create("/api/sessions/" + result.getId()))
                .body(result);
    }

    @PostMapping("/{id}/photos")
    public ResponseEntity<Void> uploadPhotos(
            @PathVariable Long id,
            @RequestParam("files") MultipartFile[] files
    ) {
        photoService.uploadPhotos(id, files);
        return ResponseEntity.ok().build();
    }

    /** PUT update existing */
    @PutMapping("/{id}")
    public ResponseEntity<PhotoSessionDto> update(@PathVariable Long id,
                                                  @RequestBody PhotoSessionDto dto) {
        var type = typeService.getById(dto.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid typeId"));
        PhotoSession ps = new PhotoSession();
        ps.setTitle(dto.getTitle());
        ps.setDescription(dto.getDescription());
        ps.setSessionDate(dto.getSessionDate());
        ps.setType(type);
        PhotoSession updated = sessionService.update(id, ps);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** DELETE  /api/sessions/{id}/photos/{filename} */
    @DeleteMapping("/{id}/photos/{filename}")
    public ResponseEntity<Void> deleteSessionPhoto(
            @PathVariable Long id,
            @PathVariable String filename) {
        photoService.deletePhotoByFilename(id, filename);
        return ResponseEntity.noContent().build();
    }

    /** Переводим Entity → DTO */
    private PhotoSessionDto toDto(PhotoSession ps) {
        List<String> filenames = ps.getPhotos() == null
                ? Collections.emptyList()
                : ps.getPhotos().stream()
                .map(Photo::getFilename)
                .collect(Collectors.toList());
        return new PhotoSessionDto(
                ps.getId(),
                ps.getTitle(),
                ps.getDescription(),
                ps.getSessionDate(),
                ps.getType().getId(),
                filenames
        );
    }
}
