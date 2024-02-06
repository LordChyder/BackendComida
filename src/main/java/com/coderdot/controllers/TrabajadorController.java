package com.coderdot.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.SucursalComida;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.services.CajaApertura.CajaAperturaService;
import com.coderdot.services.SucursalComida.SucursalComidaService;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/trabajador")
@PreAuthorize("@customAuthorizationFilter.isTrabajador()")
@SecurityRequirement(name = "bearerAuth")
public class TrabajadorController {

    private final CajaAperturaService _cajaAperturaService;
    private final SucursalTrabajadorService _sucursalTrabajadorService;
    private final SucursalComidaService _sucursalComidaService;

    public TrabajadorController(SucursalTrabajadorService sucursalTrabajadorService, 
        CajaAperturaService cajaAperturaService, SucursalComidaService sucursalComidaService) {
        this._sucursalTrabajadorService = sucursalTrabajadorService;
        this._cajaAperturaService = cajaAperturaService;
        this._sucursalComidaService = sucursalComidaService;
    }

    @GetMapping("/sucursales")
    public List<SucursalTrabajador> getMySucursales(Authentication authentication) {
        var user = (UserDetails) authentication.getPrincipal();
        return _sucursalTrabajadorService.getSucursalTrabajadoresByUserName(user.getUsername());
    }

    @GetMapping("/cajas/{sucursalId}")
    public List<CajaApertura> getCajasAperturadasNoCerradas(@PathVariable Long sucursalId, Authentication authentication) {
        var user = (UserDetails) authentication.getPrincipal();
        return _cajaAperturaService.getCajasAperturadasNoCerradasPorSucursalYUsuario(sucursalId, user.getUsername());
    }

    @GetMapping("/comidas/{sucursalId}")
    public List<SucursalComida> getComidaPorSucursal(@PathVariable Long sucursalId) {
        return _sucursalComidaService.getComidaPorSucursal(sucursalId);
    }
}