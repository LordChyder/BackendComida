package com.coderdot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/private")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public String publicEndpoint() {
        return "Este es un endpoint público.";
    }


    @GetMapping("/privates")
    @Operation(summary = "My endpoints", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("@customAuthorizationFilter.hasPermission('TEST')")
    public String pucEndpoint() {
        return "Este es un endpoint público.";
    }
}    
