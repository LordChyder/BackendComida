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

import com.coderdot.dto.request.SucursalTrabajadorRequest;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/sucursal-trabajadores")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class SucursalTrabajadorController {

    private final SucursalTrabajadorService _service;

    public SucursalTrabajadorController(SucursalTrabajadorService service) {
        this._service = service;
    }

    @GetMapping
    public List<SucursalTrabajador> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalTrabajador> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SucursalTrabajadorRequest  entity ) {
        try {
            _service.create(entity.toSucursalTrabajador());
            return ResponseEntity.ok("SucursalTrabajador creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el SucursalTrabajador: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody SucursalTrabajadorRequest entity) {
        
        SucursalTrabajador ent = new SucursalTrabajador();
        BeanUtils.copyProperties(entity, entity.toSucursalTrabajador());

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
