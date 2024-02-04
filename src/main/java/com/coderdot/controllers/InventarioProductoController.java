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

import com.coderdot.dto.request.InventarioProductoRequest;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.entities.Producto;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Inventario.InventarioService;
import com.coderdot.services.InventarioProducto.InventarioProductoService;
import com.coderdot.services.Producto.ProductoService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/inventarios-productos")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class InventarioProductoController {

    private final InventarioProductoService _service;
    private final InventarioService _iService;
    private final ProductoService _pService;

    public InventarioProductoController(InventarioProductoService service, InventarioService iService
    , ProductoService pService) {
        this._service = service;
        this._iService = iService;
        this._pService = pService;
    }

    @GetMapping("/get/inventarios")
    public List<Inventario> getAllInventarios() {
        return _iService.getAll();
    }

    @GetMapping("/get/productos")
    public List<Producto> getAllProductos() {
        return _pService.getAll();
    }

    @GetMapping
    public List<InventarioProducto> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioProducto> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody InventarioProductoRequest  entity ) {
        boolean result = _service.create(entity.toInventarioProducto());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody InventarioProductoRequest entity) {
        
        boolean result = _service.update(id, entity.toInventarioProducto());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/inventario/{inventarioId}")
    public List<InventarioProducto> getProductoPorInventario(@PathVariable Long inventarioId) {
        return _service.getProductoPorInventario(inventarioId);
    }
}