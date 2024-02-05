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

import com.coderdot.dto.request.VentaRequest;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.Sucursal;
import com.coderdot.entities.TipoDocumento;
import com.coderdot.entities.TipoPago;
import com.coderdot.entities.Venta;
import com.coderdot.models.OperationResult;
import com.coderdot.services.CajaApertura.CajaAperturaService;
import com.coderdot.services.Pedido.PedidoService;
import com.coderdot.services.Sucursal.SucursalService;
import com.coderdot.services.TipoDocumento.TipoDocumentoService;
import com.coderdot.services.TipoPago.TipoPagoService;
import com.coderdot.services.Venta.VentaService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/ventas")
@PreAuthorize("@customAuthorizationFilter.hasPermission('MANTENIMIENTO')")
@SecurityRequirement(name = "bearerAuth")
public class VentaController {

    private final VentaService _service;
    private final SucursalService _sucursalService;
    private final CajaAperturaService _cajaService;
    private final PedidoService _pedidoService;
    private final TipoPagoService _pagoService;
    private final TipoDocumentoService _documentoService;

    public VentaController(VentaService service, SucursalService sucursalService, CajaAperturaService cajaService,
    PedidoService pedidoService, TipoDocumentoService documentoService, TipoPagoService pagoService) {
        this._service = service;
        this._sucursalService = sucursalService;
        this._cajaService = cajaService;
        this._pedidoService = pedidoService;
        this._pagoService = pagoService;
        this._documentoService = documentoService;
    }

    @GetMapping
    public List<Venta> getAll() {
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@NonNull @PathVariable Long id) {
        return _service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaRequest  entity ) {

        
        if (entity.getPedido_id() == null) {
            boolean result = _service.create(entity.toVenta());
            return OperationResult.getOperationResult(result, _service.getResult().getMessages());
        } else {
            boolean result = _service.createWithPedido(entity.toVenta(), entity.getPedido_id());
            return OperationResult.getOperationResult(result, _service.getResult().getMessages());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@NonNull @PathVariable Long id, @RequestBody VentaRequest entity) {
        boolean result = _service.update(id, entity.toVenta());

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable Long id) {
        boolean result = _service.delete(id);

        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }

    @GetMapping("/get/sucursales")
    public List<Sucursal> getSucursales() {
        return _sucursalService.getAll();
    }

    @GetMapping("/get/cajas/{sucursalId}")
    public List<CajaApertura> getCajasAperturadasNoCerradas(@PathVariable Long sucursalId) {
        return _cajaService.getCajasAperturadasNoCerradas(sucursalId);
    }

    @GetMapping("/get/pedidos/{sucursalId}")
    public List<Pedido> getPedidosPorSucursal(@PathVariable Long sucursalId) {
        return _pedidoService.getPedidosActivos(sucursalId);
    }

    @GetMapping("/get/tipo-pago")
    public List<TipoPago> getPagos() {
        return _pagoService.getAll();
    }

    @GetMapping("/get/tipo-documento")
    public List<TipoDocumento> getDocumentos() {
        return _documentoService.getAll();
    }

    @PutMapping("/{ventaId}/aprobar")
    public ResponseEntity<Boolean> aprobarVenta(@PathVariable Long ventaId) {
        boolean result = _service.setClose(ventaId);
        
        return OperationResult.getOperationResult(result, _service.getResult().getMessages());
    }
}    
