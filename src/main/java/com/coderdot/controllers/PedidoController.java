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

import com.coderdot.dto.request.PedidoRequest;
import com.coderdot.entities.Mesa;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.Sucursal;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Mesa.MesaService;
import com.coderdot.services.Pedido.PedidoService;
import com.coderdot.services.Sucursal.SucursalService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/pedidos")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class PedidoController {

    private final PedidoService _service;
    private final SucursalService _sucursalService;
    private final MesaService _mesaService;

    public PedidoController(PedidoService service, SucursalService sucursalService, MesaService mesaService) {
        this._service = service;
        this._sucursalService = sucursalService;
        this._mesaService = mesaService;
    }

    @GetMapping
    public List<Pedido> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PedidoRequest  entity ) {
        boolean result = _service.create(entity.toPedido());
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody PedidoRequest entity) {
        boolean result = _service.update(id, entity.toPedido());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/get/sucursales")
    public List<Sucursal> getSucursales() {
        return _sucursalService.getAll();
    }

    @GetMapping("/get/mesas/{sucursalId}")
    public List<Mesa> getMesasPorSucursal(@PathVariable Long sucursalId) {
        return _mesaService.getMesasPorSucursal(sucursalId);
    }

    @PutMapping("/{pedidoId}/aprobar")
    public ResponseEntity<Boolean> aprobarCompra(@PathVariable Long pedidoId) {
        boolean result = _service.setClose(pedidoId);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
