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

import com.coderdot.dto.request.SucursalComidaRequest;
import com.coderdot.entities.Comida;
import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalComida;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Comida.ComidaService;
import com.coderdot.services.Sucursal.SucursalService;
import com.coderdot.services.SucursalComida.SucursalComidaService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/sucursal-comidas")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class SucursalComidaController {

    private final SucursalComidaService _service;
    private final ComidaService _comidaService;
    private final SucursalService _sucursalService;

    public SucursalComidaController(SucursalComidaService service, ComidaService comidaService, SucursalService sucursalService) {
        this._service = service;
        this._comidaService = comidaService;
        this._sucursalService = sucursalService;
    }

    @GetMapping
    public List<SucursalComida> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalComida> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SucursalComidaRequest  entity ) {
        boolean result = _service.create(entity.toSucursalComida());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody SucursalComidaRequest entity) {
        boolean result = _service.update(id, entity.toSucursalComida());
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/sucursal/{sucursalId}")
    public List<SucursalComida> getComidaPorSucursal(@PathVariable Long sucursalId) {
        return _service.getComidaPorSucursal(sucursalId);
    }

    @GetMapping("/get/comidas")
    public List<Comida> getComidas() {
        return _comidaService.getAll();
    }

    @GetMapping("/get/sucursales")
    public List<Sucursal> getSucursales() {
        return _sucursalService.getAll();
    }
}    
