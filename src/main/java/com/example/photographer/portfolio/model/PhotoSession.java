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

    private String title;
    private String description;
    private LocalDate sessionDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private SessionType type;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    public PhotoSession() { }

    // Геттер и сеттер для id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // TODO: добавить геттеры/сеттеры для остальных полей
}
