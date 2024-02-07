package com.coderdot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderdot.dto.request.TrabajadorCompraRequest;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Compra;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.Proveedor;
import com.coderdot.entities.SucursalComida;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.entities.Venta;
import com.coderdot.models.OperationResult;
import com.coderdot.services.CajaApertura.CajaAperturaService;
import com.coderdot.services.Compra.CompraService;
import com.coderdot.services.EntradaMaterial.EntradaMaterialService;
import com.coderdot.services.Inventario.InventarioService;
import com.coderdot.services.Proveedor.ProveedorService;
import com.coderdot.services.SucursalComida.SucursalComidaService;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;
import com.coderdot.services.Venta.VentaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("null")
@RestController
@RequestMapping("/api/trabajador")
@PreAuthorize("@customAuthorizationFilter.isTrabajador()")
@SecurityRequirement(name = "bearerAuth")
public class TrabajadorController {

    private final CajaAperturaService _cajaAperturaService;
    private final SucursalTrabajadorService _sucursalTrabajadorService;
    private final SucursalComidaService _sucursalComidaService;
    private final InventarioService _inventarioService;
    private final CompraService _compraService;
    private final VentaService _ventaService;
    private final EntradaMaterialService _entradaMaterialService;
    private final ProveedorService _proveedorService;

    public TrabajadorController(SucursalTrabajadorService sucursalTrabajadorService, 
        CajaAperturaService cajaAperturaService, SucursalComidaService sucursalComidaService,
        InventarioService inventarioService, CompraService compraService, VentaService ventaService,
        EntradaMaterialService entradaMaterialService, ProveedorService proveedorService) {
        this._sucursalTrabajadorService = sucursalTrabajadorService;
        this._cajaAperturaService = cajaAperturaService;
        this._sucursalComidaService = sucursalComidaService;
        this._inventarioService = inventarioService;
        this._compraService = compraService;
        this._ventaService = ventaService;
        this._entradaMaterialService = entradaMaterialService;
        this._proveedorService = proveedorService;
    }

    @GetMapping("/proveedores")
    public List<Proveedor> getProveedores() {
        return _proveedorService.getAll();
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

    @GetMapping("/inventarios/{sucursalId}")
    public List<Inventario> getAllInventariosBySucursal(@PathVariable Long sucursalId) {
        return _inventarioService.getInventarioBySucursalId(sucursalId);
    }

    @GetMapping("/compras/{sucursalId}")
    public List<Compra> getCompras(@PathVariable Long sucursalId) {
        return _compraService.obtenerComprasPorSucursal(sucursalId);
    }

    @GetMapping("/compras/byId/{compraId}")
    public Optional<Compra> getCompraById(@PathVariable Long compraId) {
        return _compraService.getById(compraId);
    }

    

    @GetMapping("/ventas/{sucursalId}")
    public List<Venta> getVentas(@PathVariable Long sucursalId) {
        return _ventaService.obtenerVentasPorSucursal(sucursalId);
    }

    @GetMapping("/ventas/byId/{ventaId}")
    public Optional<Venta> getVentaById(@PathVariable Long ventaId) {
        return _ventaService.getById(ventaId);
    }
    

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TrabajadorCompraRequest  entity ) {
        boolean result = _compraService.create(entity.toCompra());
        return OperationResult.getOperationResult(result, _compraService.getResult().getMessages());
    }

    @GetMapping("/entradas/{sucursalId}")
    public List<EntradaMaterial> getEntradas(@PathVariable Long sucursalId) {
        return _entradaMaterialService.obtenerEntradasPorSucursal(sucursalId);
    }
}