package com.coderdot.services.Reporte;

import org.springframework.stereotype.Service;

import com.coderdot.entities.Compra;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Venta;
import com.coderdot.repository.CompraRepository;
import com.coderdot.repository.EntradaMaterialRepository;
import com.coderdot.repository.VentaRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReporteService {
    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final EntradaMaterialRepository entradaMaterialRepository;

    public ReporteService(VentaRepository ventaRepository, CompraRepository compraRepository, EntradaMaterialRepository entradaMaterialRepository) {
        this.ventaRepository = ventaRepository;
        this.compraRepository = compraRepository;
        this.entradaMaterialRepository = entradaMaterialRepository;

    }
    
    
    public List<Venta> generarReporteVenta(Date fechaInicio, Date fechaFin, Long tipoPagoId, Long tipoDocumentoId) {// Si no se proporcionan filtros, retornar todas las ventas
        if (fechaInicio == null && fechaFin == null && tipoPagoId == null && tipoDocumentoId == null) {
            return ventaRepository.findAll();
        } else if (tipoPagoId != null && tipoDocumentoId == null && fechaInicio == null && fechaFin == null) {
            return ventaRepository.findByTipoPago_Id(tipoPagoId);
        } else if (tipoPagoId == null && tipoDocumentoId != null && fechaInicio == null && fechaFin == null) {
            return ventaRepository.findByTipoDocumento_Id(tipoDocumentoId);
        } else if (tipoPagoId == null && tipoDocumentoId == null && fechaInicio != null && fechaFin == null) {
            return ventaRepository.findByFechaAfter(fechaInicio);
        } else if (tipoPagoId == null && tipoDocumentoId == null && fechaInicio == null && fechaFin != null) {
            return ventaRepository.findByFechaBefore(fechaFin);
        } else if (tipoPagoId != null && tipoDocumentoId != null && fechaInicio == null && fechaFin == null) {
            return ventaRepository.findByTipoPago_IdAndTipoDocumento_Id(tipoPagoId, tipoDocumentoId);
        } else if (tipoPagoId != null && tipoDocumentoId == null && fechaInicio != null && fechaFin == null) {
            return ventaRepository.findByFechaAfterAndTipoPago_Id(fechaInicio, tipoPagoId);
        } else if (tipoPagoId != null && tipoDocumentoId == null && fechaInicio == null && fechaFin != null) {
            return ventaRepository.findByFechaBeforeAndTipoPago_Id(fechaFin, tipoPagoId);
        } else if (tipoPagoId == null && tipoDocumentoId != null && fechaInicio != null && fechaFin == null) {
            return ventaRepository.findByFechaAfterAndTipoDocumento_Id(fechaInicio, tipoDocumentoId);
        } else if (tipoPagoId == null && tipoDocumentoId != null && fechaInicio == null && fechaFin != null) {
            return ventaRepository.findByFechaBeforeAndTipoDocumento_Id(fechaFin, tipoDocumentoId);
        } else if (tipoPagoId == null && tipoDocumentoId == null && fechaInicio != null && fechaFin != null) {
            return ventaRepository.findByFechaBetween(fechaInicio, fechaFin);
        } else if (tipoPagoId != null && tipoDocumentoId != null && fechaInicio != null && fechaFin == null) {
            return ventaRepository.findByTipoPago_IdAndTipoDocumento_IdAndFechaBetween(tipoPagoId, tipoDocumentoId, fechaInicio);
        } else if (tipoPagoId != null && tipoDocumentoId == null && fechaInicio != null && fechaFin != null) {
            return ventaRepository.findByFechaAfterAndFechaBeforeAndTipoPago_IdAndTipoDocumento_Id(fechaInicio, fechaFin, tipoPagoId);
        } else if (tipoPagoId != null && tipoDocumentoId != null && fechaInicio == null && fechaFin != null) {
            return ventaRepository.findByFechaBeforeAndFechaAfterAndTipoPago_IdAndTipoDocumento_Id(fechaFin, tipoPagoId, tipoDocumentoId);
        } else if (tipoPagoId == null && tipoDocumentoId != null && fechaInicio != null && fechaFin != null) {
            return ventaRepository.findByFechaBetweenAndTipoDocumento_Id(fechaInicio, fechaFin, tipoDocumentoId);
        } else {
            return List.of();
        }
    }

    public List<Compra> generarReporteCompra(Long inventarioId, Long proveedorId, Date fechaInicio, Date fechaFin) {
       // Si no se proporcionan filtros, retorna todas las compras
        if (inventarioId == null && proveedorId == null && fechaInicio == null && fechaFin == null) {
            return compraRepository.findAll();
        } else if (inventarioId != null && proveedorId == null && fechaInicio == null && fechaFin == null) {
            return compraRepository.findByInventario_Id(inventarioId);
        } else if (inventarioId == null && proveedorId != null && fechaInicio == null && fechaFin == null) {
            return compraRepository.findByProveedor_Id(proveedorId);
        } else if (inventarioId == null && proveedorId == null && fechaInicio != null && fechaFin == null) {
            return compraRepository.findByFechaAfter(fechaInicio);
        } else if (inventarioId == null && proveedorId == null && fechaInicio == null && fechaFin != null) {
            return compraRepository.findByFechaBefore(fechaFin);
        } else if (inventarioId != null && proveedorId != null && fechaInicio == null && fechaFin == null) {
            return compraRepository.findByInventario_IdAndProveedor_Id(inventarioId, proveedorId);
        } else if (inventarioId != null && proveedorId == null && fechaInicio != null && fechaFin == null) {
            return compraRepository.findByFechaAfterAndInventario_Id(fechaInicio, inventarioId);
        } else if (inventarioId != null && proveedorId == null && fechaInicio == null && fechaFin != null) {
            return compraRepository.findByFechaBeforeAndInventario_Id(fechaFin, inventarioId);
        } else if (inventarioId == null && proveedorId != null && fechaInicio != null && fechaFin == null) {
            return compraRepository.findByFechaAfterAndProveedor_Id(fechaInicio, proveedorId);
        } else if (inventarioId == null && proveedorId != null && fechaInicio == null && fechaFin != null) {
            return compraRepository.findByFechaBeforeAndProveedor_Id(fechaFin, proveedorId);
        } else if (inventarioId == null && proveedorId == null && fechaInicio != null && fechaFin != null) {
            return compraRepository.findByFechaBetween(fechaInicio, fechaFin);
        } else if (inventarioId != null && proveedorId != null && fechaInicio != null && fechaFin == null) {
            return compraRepository.findByFechaAfterAndFechaAndInventario_IdAndProveedor_Id(fechaInicio, inventarioId, proveedorId);
        } else if (inventarioId != null && proveedorId == null && fechaInicio != null && fechaFin != null) {
            return compraRepository.findByFechaBetweenAndInventario_Id(fechaInicio, fechaFin, inventarioId);
        } else if (inventarioId != null && proveedorId != null && fechaInicio == null && fechaFin != null) {
            return compraRepository.findByFechaBeforeAndFechaAndInventario_IdAndProveedor_Id(fechaFin, inventarioId, proveedorId);
        } else if (inventarioId == null && proveedorId != null && fechaInicio != null && fechaFin != null) {
            return compraRepository.findByFechaBetweenAndProveedor_Id(fechaInicio, fechaFin, proveedorId);
        } else {
            return compraRepository.findByFechaBetweenrAndInventario_IdAndProveedor_Id(fechaInicio, fechaFin, inventarioId, proveedorId);
        }
    }

    

    public List<EntradaMaterial> generarReporteEntradaMaterial(Date fecha, Date fecha_vencimiento, Long inventarioId) {
        if (fecha == null && inventarioId == null && fecha_vencimiento == null) {
            return entradaMaterialRepository.findAll();
        } else if (fecha != null && inventarioId != null && fecha_vencimiento != null) {
            Date truncatedFecha = truncateDate(fecha);
            Date truncatedFechaVencimiento = truncateDate(fecha_vencimiento);
            return entradaMaterialRepository.findByFechaAndFechaVencimientoAndCompra_Inventario_Id(truncatedFecha, truncatedFechaVencimiento, inventarioId);
        }  else if ( fecha != null && inventarioId == null && fecha_vencimiento == null) {
            Date truncatedFecha = truncateDate(fecha);
            System.out.println(truncatedFecha);
            return entradaMaterialRepository.findByFecha(truncatedFecha);
        } else if ( fecha == null && inventarioId != null && fecha_vencimiento == null) {
            return entradaMaterialRepository.findByCompra_Inventario_Id(inventarioId);
        } else if ( fecha == null && inventarioId == null && fecha_vencimiento != null) {
            return entradaMaterialRepository.findByFechaVencimiento(fecha_vencimiento);
        } else if ( fecha != null && inventarioId != null && fecha_vencimiento == null) {
            return entradaMaterialRepository.findByFechaAndCompra_Inventario_Id(fecha, inventarioId);
        } else if ( fecha == null && inventarioId != null && fecha_vencimiento != null) {
            return entradaMaterialRepository.findByFechaVencimientoAndCompra_Inventario_Id(fecha_vencimiento, inventarioId);
        } else {
            return entradaMaterialRepository.findByFechaVencimientoAndFecha(fecha_vencimiento, fecha);
        }
    }
private Date truncateDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
}
}
