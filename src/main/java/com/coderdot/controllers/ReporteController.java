package com.coderdot.controllers;

import com.coderdot.entities.Compra;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Venta;
import com.coderdot.services.Reporte.ReporteService;

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

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/venta")
    public List<Venta> generarReporteVenta(
            @RequestParam(name = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam(name = "tipoPagoId", required = false) Long tipoPagoId,
            @RequestParam(name = "tipoDocumentoId", required = false) Long tipoDocumentoId) {
        return reporteService.generarReporteVenta(fecha, tipoPagoId, tipoDocumentoId);
    }
    
    @GetMapping("/compra")
    public List<Compra> generarReporteCompra(
            @RequestParam(required = false) Long inventarioId,
            @RequestParam(required = false) Long proveedorId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam(required = false) Long userId) {
        return reporteService.generarReporteCompra(inventarioId, proveedorId, fecha, userId);
    }

    @GetMapping("/almacen")
    public List<EntradaMaterial> generarReporteEntradaMaterial(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam(required = false) Long inventarioId) {
        return reporteService.generarReporteEntradaMaterial(fecha, inventarioId);
    }
}
