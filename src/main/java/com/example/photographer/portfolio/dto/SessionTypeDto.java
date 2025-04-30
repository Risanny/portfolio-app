package com.example.photographer.portfolio.dto;

/**
 * DTO для создания и отдачи типов фотосессий.
 */
public class SessionTypeDto {
    private Long id;
    private String name;

    public SessionTypeDto() {}

    public SessionTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
