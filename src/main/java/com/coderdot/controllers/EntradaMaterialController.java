package com.coderdot.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderdot.dto.request.EntradaMaterialRequest;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.services.EntradaMaterial.EntradaMaterialService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/entrada-material")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class EntradaMaterialController {

    private final EntradaMaterialService _service;

    public EntradaMaterialController(EntradaMaterialService service) {
        this._service = service;
    }

    @GetMapping
    public List<EntradaMaterial> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaMaterial> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EntradaMaterialRequest  entity ) {
        try {
            _service.create(entity.toEntradaMaterial());
            return ResponseEntity.ok("EntradaMaterial creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el EntradaMaterial: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody EntradaMaterialRequest entity) {
        
        EntradaMaterial ent = new EntradaMaterial();
        BeanUtils.copyProperties(entity, entity.toEntradaMaterial());

        boolean result = _service.update(id, ent);

        return result
        ? ResponseEntity.ok(true)
        : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        if (_service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}    
