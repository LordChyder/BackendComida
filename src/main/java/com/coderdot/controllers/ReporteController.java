package com.coderdot.controllers;

import com.coderdot.entities.Compra;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.Proveedor;
import com.coderdot.entities.Sucursal;
import com.coderdot.entities.TipoDocumento;
import com.coderdot.entities.TipoPago;
import com.coderdot.entities.Venta;
import com.coderdot.services.Inventario.InventarioService;
import com.coderdot.services.Proveedor.ProveedorService;
import com.coderdot.services.Reporte.ReporteService;
import com.coderdot.services.Sucursal.SucursalService;
import com.coderdot.services.TipoDocumento.TipoDocumentoService;
import com.coderdot.services.TipoPago.TipoPagoService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
    private final ReporteService reporteService;
    private final TipoPagoService _pagoService;
    private final TipoDocumentoService _documentoService;
    private final SucursalService _sucursalService;
    private final ProveedorService _proveedorService;
    private final InventarioService _inventarioService;

    public ReporteController(ReporteService reporteService, 
    TipoDocumentoService documentoService, TipoPagoService pagoService, SucursalService sucursalService, 
    InventarioService inventarioService, ProveedorService proveedorService) {
        this.reporteService = reporteService;
        this._pagoService = pagoService;
        this._documentoService = documentoService;
        this._proveedorService = proveedorService;
        this._sucursalService = sucursalService;
        this._inventarioService = inventarioService;
    }
    
    @GetMapping("/get/sucursales")
    public List<Sucursal> getSucursales() {
        return _sucursalService.getAll();
    }
    
    @GetMapping("/get/proveedores")
    public List<Proveedor> getProveedores() {
        return _proveedorService.getAll();
    }

    @GetMapping("/get/tipo-pagos")
    public List<TipoPago> getPagos() {
        return _pagoService.getAll();
    }

    @GetMapping("/get/tipo-documentos")
    public List<TipoDocumento> getDocumentos() {
        return _documentoService.getAll();
    }

    @GetMapping("/get/inventarios")
    public List<Inventario> getInventarios() {
        return _inventarioService.getAll();
    }

    @GetMapping("/venta")
    public List<Venta> generarReporteVenta(
            @RequestParam(name = "fechaInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam(name = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            @RequestParam(name = "tipoPagoId", required = false) Long tipoPagoId,
            @RequestParam(name = "tipoDocumentoId", required = false) Long tipoDocumentoId) {
        return reporteService.generarReporteVenta(fechaInicio, fechaFin, tipoPagoId, tipoDocumentoId);
    }
    
    @GetMapping("/compra")
    public List<Compra> generarReporteCompra(
            @RequestParam(required = false) Long inventarioId,
            @RequestParam(required = false) Long proveedorId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        return reporteService.generarReporteCompra(inventarioId, proveedorId, fechaInicio, fechaFin);
    }

    @GetMapping("/almacen")
    public List<EntradaMaterial> generarReporteEntradaMaterial(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_vencimiento,
            @RequestParam(required = false) Long inventarioId) {
        return reporteService.generarReporteEntradaMaterial(fecha, fecha_vencimiento, inventarioId);
    }
}
