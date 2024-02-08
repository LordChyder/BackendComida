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

import com.coderdot.dto.request.PerfilUserRequest;
import com.coderdot.entities.Perfil;
import com.coderdot.entities.PerfilUser;
import com.coderdot.models.OperationResult;
import com.coderdot.services.PerfilUser.PerfilUserService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/perfiles-users")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('SEGURIDAD')")
@SecurityRequirement(name = "bearerAuth")
public class PerfilUserController {

    private final PerfilUserService _service;

    public PerfilUserController(PerfilUserService service) {
        this._service = service;
    }

    @GetMapping
    public List<PerfilUser> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilUser> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PerfilUserRequest  entity ) {

        boolean result = _service.create(entity.toPerfilUser());
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody PerfilUserRequest entity) {
        
        PerfilUser ent = new PerfilUser();
        BeanUtils.copyProperties(entity, entity.toPerfilUser());

        boolean result = _service.update(id, ent);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{userId}/{perfilId}")
    public ResponseEntity<Void> deleteByUserAndPermiso(@NonNull @PathVariable Long userId, @NonNull @PathVariable Long perfilId) {
        boolean result = _service.deleteByUserAndPerfil(userId, perfilId);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Perfil>> getPerfilesByUserId(@PathVariable Long userId) {
        List<Perfil> perfiles = _service.getPerfilesByUserId(userId);
        return ResponseEntity.ok(perfiles);
    }

    @GetMapping("/usuario/{userId}/no-tiene")
    public ResponseEntity<List<Perfil>> getPerfilesNotInUser(@PathVariable Long userId) {
        List<Perfil> perfiles = _service.getPerfilesNotInUser(userId);
        return ResponseEntity.ok(perfiles);
    }
}    
