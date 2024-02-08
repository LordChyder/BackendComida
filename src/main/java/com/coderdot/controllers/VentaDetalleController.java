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

import com.coderdot.dto.request.VentaDetalleRequest;
import com.coderdot.entities.SucursalComida;
import com.coderdot.entities.VentaDetalle;
import com.coderdot.models.OperationResult;
import com.coderdot.services.SucursalComida.SucursalComidaService;
import com.coderdot.services.VentaDetalle.VentaDetalleService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/ventas-detalles")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class VentaDetalleController {

    private final VentaDetalleService _service;
    private final SucursalComidaService _sucursalComidaService;

    public VentaDetalleController(VentaDetalleService service, SucursalComidaService sucursalComidaService) {
        this._service = service;
        this._sucursalComidaService = sucursalComidaService;
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

    @GetMapping("/get/venta/{ventaId}")
    public List<VentaDetalle> getDetallePorVenta(@PathVariable Long ventaId) {
        return _service.getDetallePorVenta(ventaId);
    }

    @GetMapping("/get/comidas/{sucursalId}")
    public List<SucursalComida> getComidaPorSucursal(@PathVariable Long sucursalId) {
        return _sucursalComidaService.getComidaPorSucursal(sucursalId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaDetalleRequest  entity ) {
        boolean result = _service.create(entity.toVentaDetalle());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody VentaDetalleRequest entity) {
        
        boolean result = _service.update(id, entity.toVentaDetalle());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
