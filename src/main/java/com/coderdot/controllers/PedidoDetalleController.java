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

import com.coderdot.dto.request.PedidoDetalleRequest;
import com.coderdot.entities.PedidoDetalle;
import com.coderdot.entities.SucursalComida;
import com.coderdot.models.OperationResult;
import com.coderdot.services.PedidoDetalle.PedidoDetalleService;
import com.coderdot.services.SucursalComida.SucursalComidaService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/pedidos-detalles")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class PedidoDetalleController {

    private final PedidoDetalleService _service;
    private final SucursalComidaService _scService;

    public PedidoDetalleController(PedidoDetalleService service, SucursalComidaService scService) {
        this._service = service;
        this._scService = scService;
    }

    @GetMapping
    public List<PedidoDetalle> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PedidoDetalleRequest  entity ) {
        boolean result = _service.create(entity.toPedidoDetalle());
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody PedidoDetalleRequest entity) {
        boolean result = _service.update(id, entity.toPedidoDetalle());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/get/comida/{sucursalId}")
    public List<SucursalComida> getComidaPorSucursal(@PathVariable Long sucursalId) {
        return _scService.getComidaPorSucursal(sucursalId);
    }

    @GetMapping("/get/pedido/{pedidoId}")
    public List<PedidoDetalle> getDetallePorPedido(@PathVariable Long pedidoId) {
        return _service.getDetallePorPedido(pedidoId);
    }
}    
