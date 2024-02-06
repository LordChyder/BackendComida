package com.coderdot.components;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;
import com.coderdot.services.User.UserService;

@Component
public class CustomAuthorizationFilter {

    private final UserService _service;

    private final SucursalTrabajadorService _sucursalTrabajadorService;

    public CustomAuthorizationFilter(UserService service, SucursalTrabajadorService sucursalTrabajadorService) {
        this._service = service;
        this._sucursalTrabajadorService = sucursalTrabajadorService;
    }

    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return _service.userHasPermiso(authentication.getName(), permission);
    }
    
    public boolean isTrabajador() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final List<SucursalTrabajador> list = _sucursalTrabajadorService.getSucursalTrabajadoresByUserName(authentication.getName());
        
        boolean trabajador = false;
        if (list.size() > 0) {
            trabajador = true;
        }

        return trabajador;
    }
}