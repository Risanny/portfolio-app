package com.example.photographer.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключаем CSRF через лямбду, чтобы избежать deprecated API
                .csrf(csrf -> csrf.disable())

                // Правила авторизации
                .authorizeHttpRequests(auth -> auth
                        // Открываем эти эндпоинты для всех
                        .requestMatchers("/api/session-types/**", "/api/feedback/**").permitAll()
                        // Всё остальное — под защитой
                        .anyRequest().authenticated()
                )

                // HTTP Basic для защищённых URL
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
