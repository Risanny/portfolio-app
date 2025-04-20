package com.example.photographer.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;   // Имя файла (храним в файловой системе)

    @ManyToOne
    @JoinColumn(name = "session_id")
    private PhotoSession session;

    // TODO: при загрузке сохранять файлы в локальную папку /uploads/{sessionId}/
}
