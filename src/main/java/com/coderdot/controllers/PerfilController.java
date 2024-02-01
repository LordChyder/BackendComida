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

import com.coderdot.dto.request.PerfilRequest;
import com.coderdot.entities.Perfil;
import com.coderdot.services.Perfil.PerfilService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/perfiles")
@PreAuthorize("@customAuthorizationFilter.hasPermission('ALMACEN')")
@SecurityRequirement(name = "bearerAuth")
public class PerfilController {

    private final PerfilService _service;

    public PerfilController(PerfilService service) {
        this._service = service;
    }

    @GetMapping
    public List<Perfil> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@NonNull @RequestBody PerfilRequest  entity ) {
        try {
            
            Perfil ent = new Perfil();
            BeanUtils.copyProperties(entity, ent);

            _service.create(ent);
            return ResponseEntity.ok("Perfil creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el Perfil: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id,@NonNull @RequestBody PerfilRequest entity) {
        
        Perfil ent = new Perfil();
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
