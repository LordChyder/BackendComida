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

import com.coderdot.dto.request.CompraDetalleRequest;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.models.OperationResult;
import com.coderdot.services.CompraDetalle.CompraDetalleService;
import com.coderdot.services.Inventario.InventarioService;
import com.coderdot.services.InventarioProducto.InventarioProductoService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/compras-detalles")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class CompraDetalleController {

    private final CompraDetalleService _service;
    private final InventarioProductoService _ipService;
    private final InventarioService _iService;

    public CompraDetalleController(CompraDetalleService service, InventarioProductoService ipService, InventarioService iService) {
        this._service = service;
        this._iService = iService;
        this._ipService = ipService;
    }

    @GetMapping
    public List<CompraDetalle> getAll() {
        return _service.getAll();
    }

    @GetMapping("/get/inventarios")
    public List<Inventario> getAllInventarios() {
        return _iService.getAll();
    }

    @GetMapping("/get/inventarios/{sucursalId}")
    public List<Inventario> getAllInventariosBySucursal(@PathVariable Long sucursalId) {
       
        return _iService.getInventarioBySucursalId(sucursalId);
    }

    @GetMapping("/get/productos/{inventarioId}")
    public List<InventarioProducto> getProductoPorInventario(@PathVariable Long inventarioId) {
        return _ipService.getProductoPorInventario(inventarioId);
    }

    @GetMapping("/get/compra/{compraId}")
    public List<CompraDetalle> getDetallePorCompra(@PathVariable Long compraId) {
        return _service.getDetallePorCompra(compraId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDetalle> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CompraDetalleRequest  entity ) {
        boolean result = _service.create(entity.toCompraDetalle());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody CompraDetalleRequest entity) {
        
        boolean result = _service.update(id, entity.toCompraDetalle());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
