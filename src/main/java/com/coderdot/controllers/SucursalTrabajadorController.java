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

import com.coderdot.dto.request.SucursalTrabajadorRequest;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.models.OperationResult;
import com.coderdot.models.UserSummary;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;
import com.coderdot.services.User.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/sucursal-trabajadores")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class SucursalTrabajadorController {

    private final SucursalTrabajadorService _service;
    private final UserService _userService;

    public SucursalTrabajadorController(SucursalTrabajadorService service, UserService userService) {
        this._service = service;
        this._userService = userService;
    }

    @GetMapping
    public List<SucursalTrabajador> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalTrabajador> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SucursalTrabajadorRequest  entity ) {
        boolean result = _service.create(entity.toSucursalTrabajador());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody SucursalTrabajadorRequest entity) {
        boolean result = _service.update(id, entity.toSucursalTrabajador());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/my/sucursales")
    public List<SucursalTrabajador> getMySucursales(Authentication authentication) {
        var user = (UserDetails) authentication.getPrincipal();
        return _service.getSucursalTrabajadoresByUserName(user.getUsername());
    }

    @GetMapping("/sucursal/{sucursalId}")
    public List<SucursalTrabajador> getSucursalTrabajadorPorSucursal(@PathVariable Long sucursalId) {
        return _service.getSucursalTrabajadorPorSucursal(sucursalId);
    }

    @GetMapping("/get/usuarios")
    public List<UserSummary> getUsuarios() {
        return _userService.getAllUsers();
    }
}    
