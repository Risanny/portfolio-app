package com.example.photographer.portfolio.controller;

import com.example.photographer.portfolio.dto.SessionTypeDto;
import com.example.photographer.portfolio.model.SessionType;
import com.example.photographer.portfolio.service.SessionTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    /** Получить все типы (с именем, ценой и описанием) */
    @GetMapping
    public List<SessionTypeDto> listAll() {
        return service.listAll().stream()
                .map(st -> new SessionTypeDto(
                        st.getId(),
                        st.getName(),
                        st.getPrice(),
                        st.getDescription()
                ))
                .collect(Collectors.toList());
    }

    /** Получить один тип по ID */
    @GetMapping("/{id}")
    public ResponseEntity<SessionTypeDto> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(st -> ResponseEntity.ok(new SessionTypeDto(
                        st.getId(),
                        st.getName(),
                        st.getPrice(),
                        st.getDescription()
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    /** Создать новый тип */
    @PostMapping
    public ResponseEntity<SessionTypeDto> create(@RequestBody SessionTypeDto dto) {
        SessionType st = new SessionType();
        st.setName(dto.getName());
        st.setPrice(dto.getPrice());
        st.setDescription(dto.getDescription());
        SessionType created = service.create(st);

        SessionTypeDto result = new SessionTypeDto(
                created.getId(),
                created.getName(),
                created.getPrice(),
                created.getDescription()
        );

        return ResponseEntity
                .created(URI.create("/api/session-types/" + created.getId()))
                .body(result);
    }

    /** Обновить существующий тип */
    @PutMapping("/{id}")
    public ResponseEntity<SessionTypeDto> update(
            @PathVariable Long id,
            @RequestBody SessionTypeDto dto) {
        SessionType st = new SessionType();
        st.setName(dto.getName());
        st.setPrice(dto.getPrice());
        st.setDescription(dto.getDescription());
        SessionType updated = service.update(id, st);

        return ResponseEntity.ok(new SessionTypeDto(
                updated.getId(),
                updated.getName(),
                updated.getPrice(),
                updated.getDescription()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException ex) {
            // возвращаем только текст причины (ex.getReason()), а не всю обёртку
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getReason());
        }
    }

}
