package com.coderdot.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.coderdot.dto.request.UserPermisoRequest;
import com.coderdot.entities.Permiso;
import com.coderdot.entities.UserPermiso;
import com.coderdot.models.OperationResult;
import com.coderdot.services.UserPermiso.UserPermisoService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/users-permisos")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('SEGURIDAD')")
@SecurityRequirement(name = "bearerAuth")
public class UserPermisoController {

    private final UserPermisoService _service;

    public UserPermisoController(UserPermisoService service) {
        this._service = service;
    }

    @GetMapping
    public List<UserPermiso> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPermiso> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserPermisoRequest  entity ) {
        Boolean result = _service.create(entity.toUserPermiso());
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody UserPermisoRequest entity) {
        
        UserPermiso ent = new UserPermiso();
        BeanUtils.copyProperties(entity, entity.toUserPermiso());

        boolean result = _service.update(id, ent);
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{permisoId}/{userId}")
    public ResponseEntity<Void> deleteByPermisoAndUser(@NonNull @PathVariable Long permisoId, @NonNull @PathVariable Long userId) {
        boolean result = _service.deleteByPermisoAndUser(permisoId, userId);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
    
    @GetMapping("/permiso/{userId}")
    public ResponseEntity<List<Permiso>> getPermisosByUserId(@PathVariable Long userId) {
        List<Permiso> permisos = _service.getPermisosByUserId(userId);
        return ResponseEntity.ok(permisos);
    }

    @GetMapping("/permiso/{userId}/no-tiene")
    public ResponseEntity<List<Permiso>> getPermisosNotInUser(@PathVariable Long userId) {
        List<Permiso> permisos = _service.getPermisosNotInUser(userId);
        return ResponseEntity.ok(permisos);
    }
}    
