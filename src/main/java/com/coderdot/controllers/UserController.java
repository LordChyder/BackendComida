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

import com.coderdot.dto.request.SignupRequest;
import com.coderdot.models.OperationResult;
import com.coderdot.models.UserSummary;
import com.coderdot.services.User.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/users")
@PreAuthorize("@customAuthorizationFilter.hasPermission('SEGURIDAD')")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   // Obtener todos los usuarios
    @GetMapping
    public List<UserSummary> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserSummary> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OperationResult> create(@RequestBody SignupRequest user) {
        boolean result = userService.createUser(user);
        
        return OperationResult.getOperationResult(result, userService.getResult().getMessages());
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<OperationResult> updateUser(@NonNull @PathVariable Long id,  @RequestBody SignupRequest user) {
        boolean result = userService.updateUser(id, user);

        return OperationResult.getOperationResult(result, userService.getResult().getMessages());
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult> deleteUser(@NonNull @PathVariable Long id) {
        boolean result = userService.deleteUser(id);

        return OperationResult.getOperationResult(result, userService.getResult().getMessages());
    }
}    
