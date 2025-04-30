package com.example.photographer.portfolio.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для фотосессии: основные поля + список имён файлов.
 */
public class PhotoSessionDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate sessionDate;
    private Long typeId;
    private List<String> photoFilenames;

    public PhotoSessionDto() {}

    public PhotoSessionDto(Long id, String title, String description,
                           LocalDate sessionDate, Long typeId,
                           List<String> photoFilenames) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessionDate = sessionDate;
        this.typeId = typeId;
        this.photoFilenames = photoFilenames;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<String> getPhotoFilenames() {
        return photoFilenames;
    }

    public void setPhotoFilenames(List<String> photoFilenames) {
        this.photoFilenames = photoFilenames;
    }
}

// TODO: при желании добавить валидацию полей, например @NotNull для title и sessionDate
