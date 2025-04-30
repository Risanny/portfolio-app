package com.example.photographer.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "session_type")
public class SessionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Пустой конструктор для JPA
    public SessionType() {}

    // Конструктор с полями (удобно для тестов или быстрых инициализаций)
    public SessionType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттер и сеттер для id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Геттер и сеттер для name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
