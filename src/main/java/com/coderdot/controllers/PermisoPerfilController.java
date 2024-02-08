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

import com.coderdot.dto.request.PermisoPerfilRequest;
import com.coderdot.entities.Permiso;
import com.coderdot.entities.PermisoPerfil;
import com.coderdot.models.OperationResult;
import com.coderdot.services.PermisoPerfil.PermisoPerfilService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/permisos-perfiles")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('SEGURIDAD')")
@SecurityRequirement(name = "bearerAuth")
public class PermisoPerfilController {

    private final PermisoPerfilService _service;

    public PermisoPerfilController(PermisoPerfilService service) {
        this._service = service;
    }

    @GetMapping
    public List<PermisoPerfil> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoPerfil> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PermisoPerfilRequest  entity ) {
        Boolean result = _service.create(entity.toPermisoPerfil());
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody PermisoPerfilRequest entity) {
        
        PermisoPerfil ent = new PermisoPerfil();
        BeanUtils.copyProperties(entity, entity.toPermisoPerfil());

        boolean result = _service.update(id, ent);
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{permisoId}/{perfilId}")
    public ResponseEntity<Void> deleteByPermisoAndPerfil(@NonNull @PathVariable Long permisoId, @NonNull @PathVariable Long perfilId) {
        boolean result = _service.deleteByPermisoAndPerfil(permisoId, perfilId);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
    
    @GetMapping("/permiso/{perfilId}")
    public ResponseEntity<List<Permiso>> getPermisosByPerfilId(@PathVariable Long perfilId) {
        List<Permiso> permisos = _service.getPermisosByPerfilId(perfilId);
        return ResponseEntity.ok(permisos);
    }

    @GetMapping("/permiso/{perfilId}/no-tiene")
    public ResponseEntity<List<Permiso>> getPermisosNotInPerfil(@PathVariable Long perfilId) {
        List<Permiso> permisos = _service.getPermisosNotInPerfil(perfilId);
        return ResponseEntity.ok(permisos);
    }
}    
