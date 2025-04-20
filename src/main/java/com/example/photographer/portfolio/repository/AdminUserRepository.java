package com.example.photographer.portfolio.repository;

import com.example.photographer.portfolio.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
}
