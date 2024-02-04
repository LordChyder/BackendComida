package com.coderdot.controllers;

import java.util.List;

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

import com.coderdot.dto.request.MesaRequest;
import com.coderdot.entities.Mesa;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Mesa.MesaService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/mesas")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class MesaController {

    private final MesaService _service;

    public MesaController(MesaService service) {
        this._service = service;
    }

    @GetMapping
    public List<Mesa> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sucursal/{sucursalId}")
    public List<Mesa> getInventariosPorSucursal(@PathVariable Long sucursalId) {
        return _service.getMesasPorSucursal(sucursalId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MesaRequest  entity ) {
        boolean result = _service.create(entity.toMesa());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody MesaRequest entity) {
        boolean result = _service.update(id, entity.toMesa());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
