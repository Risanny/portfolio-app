package com.example.photographer.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_user")
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;  // хранить зашифрованным с BCrypt

    // TODO: позже добавить роли и права
}
