package com.example.photographer.portfolio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "photo_session")
public class PhotoSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;               // Название фотосессии
    private String description;         // Описание

    private LocalDate sessionDate;      // Дата проведения

    @ManyToOne
    @JoinColumn(name = "type_id")
    private SessionType type;           // Тип сессии (портрет, лавстори и т.п.)

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;         // Список фото

    // TODO: Добавить конструкторы, геттеры/сеттеры (можно Lombok @Data)
}

