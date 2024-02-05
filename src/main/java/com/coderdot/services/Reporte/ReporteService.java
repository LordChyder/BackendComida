package com.coderdot.services.Reporte;

import org.springframework.stereotype.Service;

import com.coderdot.entities.Compra;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Venta;
import com.coderdot.repository.CompraRepository;
import com.coderdot.repository.EntradaMaterialRepository;
import com.coderdot.repository.VentaRepository;

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
    
    
    public List<Venta> generarReporteVenta(Date fecha, Long tipoPagoId, Long tipoDocumentoId) {
        if (fecha == null && tipoPagoId == null && tipoDocumentoId == null) {
            // Si no se proporcionan filtros, retorna todas las ventas
            return ventaRepository.findAll();
        } else if (fecha == null && tipoPagoId != null && tipoDocumentoId == null) {
            // Filtra por TipoPago
            return ventaRepository.findByTipoPago_Id(tipoPagoId);
        } else if (fecha == null && tipoPagoId == null && tipoDocumentoId != null) {
            // Filtra por TipoPago
            return ventaRepository.findByTipoDocumento_Id(tipoDocumentoId);
        } else if (fecha != null && tipoPagoId == null && tipoDocumentoId == null) {
            // Filtra por Fecha
            return ventaRepository.findByFecha(fecha);
        } else if (fecha == null && tipoPagoId != null && tipoDocumentoId != null) {
            // Filtra por Fecha
            return ventaRepository.findByTipoDocumento_IdAndTipoPago_Id(tipoDocumentoId, tipoPagoId);
        } else if (fecha != null && tipoPagoId != null && tipoDocumentoId == null) {
            return ventaRepository.findByFechaAndTipoPago_Id(fecha, tipoPagoId);
        }else if (fecha != null && tipoPagoId == null && tipoDocumentoId != null) {
            return ventaRepository.findByFechaAndTipoDocumento_Id(fecha, tipoPagoId);
        }else {
            // Filtra según los IDs de TipoPago y TipoDocumento proporcionados
            return ventaRepository.findByFechaAndTipoPago_IdAndTipoDocumento_Id(fecha, tipoPagoId, tipoDocumentoId);
        }
    }


    public List<Compra> generarReporteCompra(Long inventarioId, Long proveedorId, Date fecha, Long userId) {
        if (fecha == null && inventarioId == null && proveedorId == null && userId == null) {
            // Si no se proporcionan filtros, retorna todas las compras
            return compraRepository.findAll();
        } else if (fecha == null && inventarioId != null && proveedorId == null && userId == null) {
            // Filtra por Inventario
            return compraRepository.findByInventario_Id(inventarioId);
        } else if (fecha == null && inventarioId == null && proveedorId != null && userId == null) {
            // Filtra por Proveedor
            return compraRepository.findByProveedor_Id(proveedorId);
        } else if (fecha == null && inventarioId == null && proveedorId == null && userId != null) {
            // Filtra por Usuario
            return compraRepository.findByUser_Id(userId);
        } else if (fecha != null && inventarioId == null && proveedorId == null && userId == null) {
            // Filtra por Fecha
            return compraRepository.findByFecha(fecha);
        } else if (fecha != null && inventarioId != null && proveedorId == null && userId == null) {
            // Filtra por Fecha e Inventario
            return compraRepository.findByFechaAndInventario_Id(fecha, inventarioId);
        } else if (fecha != null && inventarioId == null && proveedorId != null && userId == null) {
            // Filtra por Fecha y Proveedor
            return compraRepository.findByFechaAndProveedor_Id(fecha, proveedorId);
        } else if (fecha != null && inventarioId == null && proveedorId == null && userId != null) {
            // Filtra por Fecha y Usuario
            return compraRepository.findByFechaAndUser_Id(fecha, userId);
        } else if (fecha == null && inventarioId != null && proveedorId != null && userId == null) {
            // Filtra por Inventario y Proveedor
            return compraRepository.findByInventario_IdAndProveedor_Id(inventarioId, proveedorId);
        } else if (fecha == null && inventarioId != null && proveedorId == null && userId != null) {
            // Filtra por Inventario y Usuario
            return compraRepository.findByInventario_IdAndUser_Id(inventarioId, userId);
        } else if (fecha == null && inventarioId == null && proveedorId != null && userId != null) {
            // Filtra por Proveedor y Usuario
            return compraRepository.findByProveedor_IdAndUser_Id(proveedorId, userId);
        } else if (fecha != null && inventarioId != null && proveedorId == null && userId == null) {
            // Filtra por Fecha, Inventario y Proveedor
            return compraRepository.findByFechaAndInventario_IdAndProveedor_Id(fecha, inventarioId, proveedorId);
        } else if (fecha != null && inventarioId != null && proveedorId != null && userId == null) {
            // Filtra por Fecha, Inventario y Proveedor
            return compraRepository.findByFechaAndInventario_IdAndProveedor_Id(fecha, inventarioId, proveedorId);
        } else if (fecha != null && inventarioId != null && proveedorId == null && userId != null) {
            // Filtra por Fecha, Inventario y Usuario
            return compraRepository.findByFechaAndInventario_IdAndUser_Id(fecha, inventarioId, userId);
        } else if (fecha != null && inventarioId == null && proveedorId != null && userId != null) {
            // Filtra por Fecha, Proveedor y Usuario
            return compraRepository.findByFechaAndProveedor_IdAndUser_Id(fecha, proveedorId, userId);
        } else {
            // Filtra según los IDs proporcionados
            return compraRepository.findByFechaAndInventario_IdAndProveedor_IdAndUser_Id(
                    fecha, inventarioId, proveedorId, userId);
        }
    }

    

    public List<EntradaMaterial> generarReporteEntradaMaterial(Date fecha, Long inventarioId) {
        if (fecha == null && inventarioId == null) {
            // Si no se proporcionan filtros, retorna todas las entradas de material
            return entradaMaterialRepository.findAll();
        } else if (fecha != null && inventarioId != null) {
            // Filtra por Fecha e Inventario
            return entradaMaterialRepository.findByFechaAndCompra_Inventario_Id(fecha, inventarioId);
        } else if (fecha == null && inventarioId != null) {
            // Filtra por Inventario
            return entradaMaterialRepository.findByCompra_Inventario_Id(inventarioId);
        } else {
            // Filtra por Fecha
            return entradaMaterialRepository.findByFecha(fecha);
        } 
    }
}
