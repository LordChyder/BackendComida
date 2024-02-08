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

import com.coderdot.dto.request.CategoriaSavedRequest;
import com.coderdot.entities.Categoria;
import com.coderdot.models.OperationResult;
import com.coderdot.services.Categoria.CategoriaService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/categorias")
//@PreAuthorize("@customAuthorizationFilter.hasPermission('ALMACEN')")
@SecurityRequirement(name = "bearerAuth")
public class CategoriaController {

    private final CategoriaService _service;

    public CategoriaController(CategoriaService service) {
        this._service = service;;
    }

    @GetMapping
    public List<Categoria> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@NonNull @RequestBody CategoriaSavedRequest  entity ) {
        Categoria ent = new Categoria();
        BeanUtils.copyProperties(entity, ent);

        boolean result = _service.create(ent);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id,@NonNull @RequestBody CategoriaSavedRequest entity) {
        
        Categoria ent = new Categoria();
        BeanUtils.copyProperties(entity, ent);

        boolean result = _service.update(id, ent);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
