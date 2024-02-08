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

import com.coderdot.dto.request.CajaAperturaRequest;
import com.coderdot.entities.Caja;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Caja.CajaService;
import com.coderdot.services.CajaApertura.CajaAperturaService;
import com.coderdot.services.Sucursal.SucursalService;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/cajas-aperturas")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class CajaAperturaController {

    private final CajaAperturaService _service;
    private final SucursalTrabajadorService _ctService;
    private final SucursalService _sucursalService;
    private final CajaService _cajaService;

    public CajaAperturaController(SucursalTrabajadorService ctService, CajaAperturaService service, 
    SucursalService sucursalService, CajaService cajaService) {
        this._service = service;
        this._cajaService = cajaService;
        this._sucursalService = sucursalService;
        this._ctService = ctService;
    }

    @GetMapping
    public List<CajaApertura> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajaApertura> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CajaAperturaRequest  entity ) {
        boolean result = _service.create(entity.toCajaApertura());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody CajaAperturaRequest entity) {
        boolean result = _service.update(id, entity.toCajaApertura());

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

    @GetMapping("/get/usuarios/{sucursalId}")
    public List<SucursalTrabajador> getUsuarios(@PathVariable Long sucursalId) {
        return _ctService.getSucursalTrabajadorPorSucursal(sucursalId);
    }

    @GetMapping("/get/cajas/{sucursalId}")
    public List<Caja> getCajas(@PathVariable Long sucursalId) {
        return _cajaService.getCajasPorSucursal(sucursalId);
    }
    @PutMapping("/{cajaAId}/cerrar")
    public ResponseEntity<Boolean> aprobarCompra(@PathVariable Long cajaAId) {
        boolean result = _service.setClose(cajaAId);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}