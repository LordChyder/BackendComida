package com.coderdot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderdot.dto.request.CompraDetalleRequest;
import com.coderdot.dto.request.EntradaMaterialRequest;
import com.coderdot.dto.request.PedidoDetalleRequest;
import com.coderdot.dto.request.TrabajadorCompraRequest;
import com.coderdot.dto.request.TrabajadorPedidoRequest;
import com.coderdot.dto.response.MesaPedidoDTO;
import com.coderdot.dto.response.PedidoDetalleDTO;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Compra;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.PedidoDetalle;
import com.coderdot.entities.Proveedor;
import com.coderdot.entities.SucursalComida;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.entities.Venta;
import com.coderdot.models.OperationResult;
import com.coderdot.services.CajaApertura.CajaAperturaService;
import com.coderdot.services.Compra.CompraService;
import com.coderdot.services.CompraDetalle.CompraDetalleService;
import com.coderdot.services.EntradaMaterial.EntradaMaterialService;
import com.coderdot.services.InventarioProducto.InventarioProductoService;
import com.coderdot.services.Mesa.MesaService;
import com.coderdot.services.Pedido.PedidoService;
import com.coderdot.services.PedidoDetalle.PedidoDetalleService;
import com.coderdot.services.Inventario.InventarioService;
import com.coderdot.services.Proveedor.ProveedorService;
import com.coderdot.services.SucursalComida.SucursalComidaService;
import com.coderdot.services.SucursalTrabajador.SucursalTrabajadorService;
import com.coderdot.services.Venta.VentaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/trabajador")
//@PreAuthorize("@customAuthorizationFilter.isTrabajador()")
@SecurityRequirement(name = "bearerAuth")
public class TrabajadorController {

    private final CajaAperturaService _cajaAperturaService;
    private final SucursalTrabajadorService _sucursalTrabajadorService;
    private final SucursalComidaService _sucursalComidaService;
    private final InventarioService _inventarioService;
    private final InventarioProductoService _inventarioProductoService;
    private final CompraService _compraService;
    private final VentaService _ventaService;
    private final EntradaMaterialService _entradaMaterialService;
    private final ProveedorService _proveedorService;
    private final CompraDetalleService _compraDetalleService;
    private final MesaService _mesaService;  
    private final PedidoService _pedidoService;  
    private final PedidoDetalleService _pedidoDetalleService;  

    public TrabajadorController(SucursalTrabajadorService sucursalTrabajadorService, 
        CajaAperturaService cajaAperturaService, SucursalComidaService sucursalComidaService,
        InventarioService inventarioService, CompraService compraService, VentaService ventaService,
        EntradaMaterialService entradaMaterialService, ProveedorService proveedorService,
        CompraDetalleService compraDetalleService, InventarioProductoService inventarioProductoService,
        MesaService mesaService, PedidoService pedidoService, PedidoDetalleService pedidoDetalleService) {
        this._sucursalTrabajadorService = sucursalTrabajadorService;
        this._cajaAperturaService = cajaAperturaService;
        this._sucursalComidaService = sucursalComidaService;
        this._inventarioService = inventarioService;
        this._compraService = compraService;
        this._ventaService = ventaService;
        this._entradaMaterialService = entradaMaterialService;
        this._proveedorService = proveedorService;
        this._inventarioProductoService = inventarioProductoService;
        this._compraDetalleService = compraDetalleService;
        this._mesaService = mesaService;
        this._pedidoService = pedidoService;
        this._pedidoDetalleService = pedidoDetalleService;
    }
    //#region GETS

    @GetMapping("/proveedores")
    public List<Proveedor> getProveedores() {
        return _proveedorService.getAll();
    }

    @GetMapping("/sucursales")
    public List<SucursalTrabajador> getMySucursales(Authentication authentication) {
        var user = (UserDetails) authentication.getPrincipal();
        return _sucursalTrabajadorService.getSucursalTrabajadoresByUserName(user.getUsername());
    }
    
    @GetMapping("/productos/{inventarioId}")
    public List<InventarioProducto> getProductoPorInventario(@PathVariable Long inventarioId) {
        return _inventarioProductoService.getProductoPorInventario(inventarioId);
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

    //#endregion

    //#region PEDIDOS
    @GetMapping("/mesas/sucursal/{sucursalId}")
    public List<MesaPedidoDTO> getMesasPorSucursal(@PathVariable Long sucursalId) {
        return _mesaService.getMesasConPedidosPorSucursal(sucursalId);
    }

    @GetMapping("/pedidos/byId/{id}")
    public ResponseEntity<Pedido> getPedidoById(@NonNull @PathVariable Long id) {
        return _pedidoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/pedidos")
    public ResponseEntity<?> createPedido(@RequestBody TrabajadorPedidoRequest  entity ) {
        boolean result = _pedidoService.create(entity.toPedido());
        
        return OperationResult.getOperationResult(result, _pedidoService.getResult().getMessages());
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Boolean> updatePedido(@NonNull @PathVariable Long id, @RequestBody TrabajadorPedidoRequest entity) {
        boolean result = _pedidoService.updateTrabajador(id, entity.toPedido());

        return OperationResult.getOperationResult(result, _pedidoService.getResult().getMessages());
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> deletePedido(@NonNull @PathVariable Long id) {
        
        boolean result = _pedidoService.delete(id);
        
        return OperationResult.getOperationResult(result, _pedidoService.getResult().getMessages());
    }

    @PutMapping("/pedidos/{pedidoId}/aprobar")
    public ResponseEntity<Boolean> aprobarPedido(@PathVariable Long pedidoId) {
        boolean result = _pedidoService.setClose(pedidoId);
        
        return OperationResult.getOperationResult(result, _pedidoService.getResult().getMessages());
    }

    @PutMapping("/pedidos/{pedidoId}/anular")
    public ResponseEntity<Boolean> anularPedido(@PathVariable Long pedidoId) {
        boolean result = _pedidoService.actualizarAnulado(pedidoId);
        
        return OperationResult.getOperationResult(result, _pedidoService.getResult().getMessages());
    }
    
    @GetMapping("/pedidos-detalles/byId/{id}")
    public ResponseEntity<PedidoDetalle> getPedidoDetalleById(@NonNull @PathVariable Long id) {
        return _pedidoDetalleService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedidos-detalles/{pedidoId}")
    public List<PedidoDetalleDTO> getPedidoDetallePorPedido(@PathVariable Long pedidoId) {
        return _pedidoDetalleService.findPedidoDetalleWithPrecioByPedidoId(pedidoId);
    }

    @PostMapping("/pedidos-detalles")
    public ResponseEntity<?> createPedidoDetalle(@RequestBody PedidoDetalleRequest  entity ) {
        boolean result = _pedidoDetalleService.create(entity.toPedidoDetalle());
        
        return OperationResult.getOperationResult(result, _pedidoDetalleService.getResult().getMessages());
    }

    @PutMapping("/pedidos-detalles/{id}")
    public ResponseEntity<Boolean> updatePedidoDetalle(@NonNull @PathVariable Long id, @RequestBody PedidoDetalleRequest entity) {
        boolean result = _pedidoDetalleService.update(id, entity.toPedidoDetalle());

        return OperationResult.getOperationResult(result, _pedidoDetalleService.getResult().getMessages());
    }

    @DeleteMapping("/pedidos-detalles/{id}")
    public ResponseEntity<Void> deletePedidoDetalle(@NonNull @PathVariable Long id) {
        
        boolean result = _pedidoDetalleService.delete(id);
        
        return OperationResult.getOperationResult(result, _pedidoDetalleService.getResult().getMessages());
    }
    
    //#endregion

    @GetMapping("/ventas/{sucursalId}")
    public List<Venta> getVentas(@PathVariable Long sucursalId) {
        return _ventaService.obtenerVentasPorSucursal(sucursalId);
    }

    @SuppressWarnings("null")
    @GetMapping("/ventas/byId/{ventaId}")
    public Optional<Venta> getVentaById(@PathVariable Long ventaId) {
        return _ventaService.getById(ventaId);
    }

    //#region COMPRA

    @GetMapping("/compras/{sucursalId}")
    public List<Compra> getCompras(@PathVariable Long sucursalId) {
        return _compraService.obtenerComprasPorSucursal(sucursalId);
    }

    @SuppressWarnings("null")
    @GetMapping("/compras/byId/{compraId}")
    public Optional<Compra> getCompraById(@PathVariable Long compraId) {
        return _compraService.getById(compraId);
    }
    
    @SuppressWarnings("null")
    @PostMapping("/compras")
    public ResponseEntity<?> create(@RequestBody TrabajadorCompraRequest entity) {
        boolean result = _compraService.createByTrabajador(entity);
        return OperationResult.getOperationResult(result, _compraService.getResult().getMessages());
    }

    @SuppressWarnings("null")
    @PutMapping("/compras/{id}")
    public ResponseEntity<?> update(@NonNull @PathVariable Long id, @RequestBody TrabajadorCompraRequest entity) {
        
        boolean result = _compraService.updateByTrabajador(id, entity);

        return OperationResult.getOperationResult(result, _compraService.getResult().getMessages());
    }

    @PutMapping("/compras/{compraId}/aprobar")
    public ResponseEntity<Boolean> aprobarCompra(@PathVariable Long compraId) {
        boolean result = _compraService.setClose(compraId);
        
        return OperationResult.getOperationResult(result, _compraService.getResult().getMessages());
    }

    @DeleteMapping("/compras/{id}")
    public ResponseEntity<Void> deleteCompra(@NonNull @PathVariable Long id) {
        boolean result = _compraService.delete(id);
        
        return OperationResult.getOperationResult(result, _compraService.getResult().getMessages());
    }

    @SuppressWarnings("null")
    @GetMapping("/compras-detalle/byId/{compraId}")
    public Optional<CompraDetalle> getCompraDetalleById(@PathVariable Long compraId) {
        return _compraDetalleService.getById(compraId);
    }
    
    @GetMapping("/compras-detalle/{compraId}")
    public List<CompraDetalle> getDetallePorCompra(@PathVariable Long compraId) {
        return _compraDetalleService.getDetallePorCompra(compraId);
    }
    
    @PostMapping("/compras-detalle")
    public ResponseEntity<?> createCompraDetalle(@RequestBody CompraDetalleRequest  entity ) {
        boolean result = _compraDetalleService.create(entity.toCompraDetalle());

        return OperationResult.getOperationResult(result, _compraDetalleService.getResult().getMessages());
    }

    @PutMapping("/compras-detalle/{id}")
    public ResponseEntity<Boolean> updateCompraDetalle(@NonNull @PathVariable Long id, @RequestBody CompraDetalleRequest entity) {
        
        boolean result = _compraDetalleService.update(id, entity.toCompraDetalle());

        return OperationResult.getOperationResult(result, _compraDetalleService.getResult().getMessages());
    }

    @DeleteMapping("/compras-detalle/{id}")
    public ResponseEntity<Void> deleteCompraDetalle(@NonNull @PathVariable Long id) {
        boolean result = _compraDetalleService.delete(id);
        
        return OperationResult.getOperationResult(result, _compraDetalleService.getResult().getMessages());
    }
    //#endregion

    //#region ENTRADAS
    @GetMapping("/entradas/{sucursalId}")
    public List<EntradaMaterial> getEntradas(@PathVariable Long sucursalId) {
        return _entradaMaterialService.obtenerEntradasPorSucursal(sucursalId);
    }
    
    @GetMapping("/entradas/byId/{id}")
    public ResponseEntity<EntradaMaterial> getById(@NonNull @PathVariable Long id) {
        return _entradaMaterialService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/entradas")
    public ResponseEntity<?> create(@RequestBody EntradaMaterialRequest  entity ) {
        boolean result = _entradaMaterialService.create(entity.toEntradaMaterial());

        return OperationResult.getOperationResult(result, _entradaMaterialService.getResult().getMessages());
    }

    @PutMapping("/entradas/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody EntradaMaterialRequest entity) {
        
        boolean result = _entradaMaterialService.update(id,  entity.toEntradaMaterial());
        
        return OperationResult.getOperationResult(result, _entradaMaterialService.getResult().getMessages());
    }

    @DeleteMapping("/entradas/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _entradaMaterialService.delete(id);
        
        return OperationResult.getOperationResult(result, _entradaMaterialService.getResult().getMessages());
    }

    @GetMapping("/entradas/compras/{id}/no-entradas-y-aprobadas")
    public List<Compra> obtenerComprasEntradaFalseYEstadoTrue(@NonNull @PathVariable Long id) {
        return _compraService.obtenerComprasEntradaFalseAndEstadoTrueAndInventarioSucursalId(id);
    }

    //#endregion
}