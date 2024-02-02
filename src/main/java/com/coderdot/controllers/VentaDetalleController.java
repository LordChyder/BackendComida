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

import com.coderdot.dto.request.VentaDetalleRequest;
import com.coderdot.entities.VentaDetalle;
import com.coderdot.services.VentaDetalle.VentaDetalleService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/ventas-detalles")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class VentaDetalleController {

    private final VentaDetalleService _service;

    public VentaDetalleController(VentaDetalleService service) {
        this._service = service;
    }

    @GetMapping
    public List<VentaDetalle> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDetalle> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaDetalleRequest  entity ) {
        try {
            _service.create(entity.toVentaDetalle());
            return ResponseEntity.ok("VentaDetalle creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el VentaDetalle: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody VentaDetalleRequest entity) {
        
        VentaDetalle ent = new VentaDetalle();
        BeanUtils.copyProperties(entity, entity.toVentaDetalle());

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
