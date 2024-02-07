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

import com.coderdot.dto.request.EntradaMaterialRequest;
import com.coderdot.entities.Compra;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Compra.CompraService;
import com.coderdot.services.EntradaMaterial.EntradaMaterialService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/entrada-material")
@PreAuthorize("@customAuthorizationFilter.hasPermission('ALMACEN')")
@SecurityRequirement(name = "bearerAuth")
public class EntradaMaterialController {

    private final EntradaMaterialService _service;
    private final CompraService _cService;

    public EntradaMaterialController(EntradaMaterialService service, CompraService cService) {
        this._service = service;
        this._cService = cService;
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
        boolean result = _service.create(entity.toEntradaMaterial());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody EntradaMaterialRequest entity) {
        
        boolean result = _service.update(id,  entity.toEntradaMaterial());
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/get/no-entradas-y-aprobadas")
    public List<Compra> obtenerComprasEntradaFalseYEstadoTrue() {
        return _cService.obtenerComprasEntradaFalseYEstadoTrue();
    }
}    
