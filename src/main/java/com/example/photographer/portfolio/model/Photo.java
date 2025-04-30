package com.example.photographer.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;   // Имя файла

    @ManyToOne
    @JoinColumn(name = "session_id")
    private PhotoSession session;

    public Photo() {}

    // --- Добавьте эти методы: ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public PhotoSession getSession() {
        return session;
    }

    public void setSession(PhotoSession session) {
        this.session = session;
    }
}
