package com.example.photographer.portfolio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "session_type_id")
    private SessionType sessionType;  // Выбор типа сессии из админки

    private LocalDateTime submittedAt = LocalDateTime.now();  // Дата отправки

    // TODO: добавить флаг “обработано” для админки
}
