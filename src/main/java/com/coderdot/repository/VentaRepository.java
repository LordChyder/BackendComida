package com.coderdot.repository;

import com.coderdot.entities.Venta;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    
    @Modifying
    @Query("UPDATE Venta c SET c.estado = true WHERE c.id = :ventaId")
    void actualizarEstado(@Param("ventaId") Long ventaId);

    @Modifying
    @Query("UPDATE Venta c SET c.anulado = true WHERE c.id = :ventaId")
    void actualizarAnular(@Param("ventaId") Long ventaId);

    @Modifying
    @Query("UPDATE Venta c SET c.anulado = false WHERE c.id = :ventaId")
    void actualizarAnularFalse(@Param("ventaId") Long ventaId);
    
    @Query("SELECT cd FROM Venta cd WHERE cd.id = :ventaId")
    Venta findYesById(@Param("ventaId") Long ventaId);

    List<Venta> findByCajaAperturaCajaSucursalId(Long sucursalId);

    @Query("SELECT v FROM Venta v WHERE v.fecha >= :fechaInicio AND v.fecha < :fechaFin AND v.cajaApertura.caja.sucursal.id = :sucursalId")
    List<Venta> findByFechaBetweenAndSucursal(Date fechaInicio, Date fechaFin, Long sucursalId);



    List<Venta> findByFechaAndTipoPago_IdAndTipoDocumento_Id(Date fecha, Long tipoPagoId, Long tipoDocumentoId);

    List<Venta> findByFecha(Date fecha);

    List<Venta> findByTipoPago_Id(Long tipoPagoId);

    List<Venta> findByTipoDocumento_Id(Long tipoDocumentoId);

    List<Venta> findByTipoDocumento_IdAndTipoPago_Id(Long tipoDocumentoId, Long tipoPagoId);

    List<Venta> findByFechaAndTipoPago_Id(Date fecha, Long tipoPagoId);
    List<Venta> findByFechaAndTipoDocumento_Id(Date fecha, Long tipoDocumentoId);
}
