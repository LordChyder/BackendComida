package com.coderdot.components;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.coderdot.services.UserService;

@Component
public class CustomAuthorizationFilter {

    private final UserService _service;

    public CustomAuthorizationFilter(UserService service) {
        this._service = service;
    }

    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return _service.userHasPermiso(authentication.getName(), permission);
    }
}