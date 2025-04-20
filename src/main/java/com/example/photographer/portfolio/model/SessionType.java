package com.example.photographer.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "session_type")
public class SessionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;   // Например: "Портфолио", "Семейная", "Лавстори"

    // TODO: в админке дать CRUD для SessionType
}
