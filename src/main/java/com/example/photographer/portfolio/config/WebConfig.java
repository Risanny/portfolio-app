package com.example.photographer.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация для раздачи загруженных файлов по URL /uploads/**
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Директория, куда сохраняются файлы (app.upload.dir из application.properties)
    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Любые запросы к /uploads/** будут читаться из файловой системы
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
}
