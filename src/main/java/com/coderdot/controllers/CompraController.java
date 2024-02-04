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

import com.coderdot.dto.request.CompraRequest;
import com.coderdot.entities.Compra;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.Proveedor;
import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Compra.CompraService;
import com.coderdot.services.Inventario.InventarioService;
import com.coderdot.services.Proveedor.ProveedorService;
import com.coderdot.services.Sucursal.SucursalService;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/compras")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class CompraController {

    private final CompraService _service;
    private final SucursalTrabajadorService _ctService;
    private final ProveedorService _proveedorService;
    private final SucursalService _sucursalService;
    private final InventarioService _iService;

    public CompraController(CompraService service, SucursalTrabajadorService ctService, SucursalService sucursalService, InventarioService iService, ProveedorService proveedorService) {
        this._service = service;
        this._proveedorService = proveedorService;
        this._iService = iService;
        this._sucursalService = sucursalService;
        this._ctService = ctService;
    }

    @GetMapping
    public List<Compra> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CompraRequest  entity ) {
        boolean result = _service.create(entity.toCompra());
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody CompraRequest entity) {
        
        boolean result = _service.update(id, entity.toCompra());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    
    @GetMapping("/get/usuarios/{sucursalId}")
    public List<SucursalTrabajador> getUsuarios(@PathVariable Long sucursalId) {
        return _ctService.getSucursalTrabajadorPorSucursal(sucursalId);
    }
    
    @GetMapping("/get/proveedores")
    public List<Proveedor> getProveedores() {
        return _proveedorService.getAll();
    }
    
    @GetMapping("/get/sucursales")
    public List<Sucursal> getSucursales() {
        return _sucursalService.getAll();
    }
    @GetMapping("/get/inventarios/{sucursalId}")
    public List<Inventario> getAllInventariosBySucursal(@PathVariable Long sucursalId) {
       
        return _iService.getInventarioBySucursalId(sucursalId);
    }

    @PutMapping("/{compraId}/aprobar")
    public ResponseEntity<Boolean> aprobarCompra(@PathVariable Long compraId) {
        boolean result = _service.setClose(compraId);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
