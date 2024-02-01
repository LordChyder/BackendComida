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

import com.coderdot.dto.request.PermisoRequest;
import com.coderdot.entities.Permiso;
import com.coderdot.services.Permiso.PermisoService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/permisos")
@PreAuthorize("@customAuthorizationFilter.hasPermission('ALMACEN')")
@SecurityRequirement(name = "bearerAuth")
public class PermisoController {

    private final PermisoService _service;

    public PermisoController(PermisoService service) {
        this._service = service;
    }

    @GetMapping
    public List<Permiso> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@NonNull @RequestBody PermisoRequest  entity ) {
        try {
            
            Permiso ent = new Permiso();
            BeanUtils.copyProperties(entity, ent);

            _service.create(ent);
            return ResponseEntity.ok("Permiso creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el Permiso: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id,@NonNull @RequestBody PermisoRequest entity) {
        
        Permiso ent = new Permiso();
        BeanUtils.copyProperties(entity, ent);

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
