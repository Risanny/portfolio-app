package com.example.photographer.portfolio.controller;

import com.example.photographer.portfolio.dto.SessionTypeDto;
import com.example.photographer.portfolio.model.SessionType;
import com.example.photographer.portfolio.service.SessionTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/session-types")
public class SessionTypeController {

    private final SessionTypeService service;

    public SessionTypeController(SessionTypeService service) {
        this.service = service;
    }

    /** Получить все типы */
    @GetMapping
    public List<SessionTypeDto> listAll() {
        return service.listAll().stream()
                .map(st -> new SessionTypeDto(st.getId(), st.getName()))
                .collect(Collectors.toList());
    }

    /** Получить по ID */
    @GetMapping("/{id}")
    public ResponseEntity<SessionTypeDto> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(st -> ResponseEntity.ok(new SessionTypeDto(st.getId(), st.getName())))
                .orElse(ResponseEntity.notFound().build());
    }

    /** Создать новый тип */
    @PostMapping
    public ResponseEntity<SessionTypeDto> create(@RequestBody SessionTypeDto dto) {
        SessionType st = new SessionType();
        st.setName(dto.getName());
        SessionType created = service.create(st);
        SessionTypeDto resultDto = new SessionTypeDto(created.getId(), created.getName());
        // Возвращаем 201 Created и header Location
        return ResponseEntity
                .created(URI.create("/api/session-types/" + created.getId()))
                .body(resultDto);
    }

    /** Обновить существующий тип */
    @PutMapping("/{id}")
    public ResponseEntity<SessionTypeDto> update(
            @PathVariable Long id,
            @RequestBody SessionTypeDto dto) {
        SessionType st = new SessionType();
        st.setName(dto.getName());
        SessionType updated = service.update(id, st);
        return ResponseEntity.ok(new SessionTypeDto(updated.getId(), updated.getName()));
    }

    /** Удалить тип */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
