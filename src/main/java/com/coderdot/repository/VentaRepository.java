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
    @Query("UPDATE Venta v SET v.estado = true WHERE v.id = :ventaId")
    void actualizarEstado(@Param("ventaId") Long ventaId);

    @Modifying
    @Query("UPDATE Venta v SET v.anulado = true WHERE v.id = :ventaId")
    void actualizarAnular(@Param("ventaId") Long ventaId);

    @Modifying
    @Query("UPDATE Venta v SET v.anulado = false WHERE v.id = :ventaId")
    void actualizarAnularFalse(@Param("ventaId") Long ventaId);
    
    @Query("SELECT cd FROM Venta cd WHERE cd.id = :ventaId")
    Venta findYesById(@Param("ventaId") Long ventaId);

    List<Venta> findByCajaAperturaCajaSucursalId(Long sucursalId);

    @Query("SELECT v FROM Venta v WHERE v.fecha >= :fechaInicio AND v.fecha <= :fechaFin AND v.cajaApertura.caja.sucursal.id = :sucursalId")
    List<Venta> findByFechaBetweenAndSucursal(Date fechaInicio, Date fechaFin, Long sucursalId);


    List<Venta> findByFecha(Date fecha);

    List<Venta> findByTipoPago_Id(Long tipoPagoId);

    List<Venta> findByTipoDocumento_Id(Long tipoDocumentoId);

    List<Venta> findByTipoDocumento_IdAndTipoPago_Id(Long tipoDocumentoId, Long tipoPagoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) >= DATE(:fechaInicio)")
    List<Venta> findByFechaAfter(Date fechaInicio);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) <= DATE(:fechaFin)")
    List<Venta> findByFechaBefore(Date fechaFin);

    List<Venta> findByTipoPago_IdAndTipoDocumento_Id(Long tipoPagoId, Long tipoDocumentoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) >= DATE(:fechaInicio) AND v.tipoPago.id = :tipoPagoId")
    List<Venta> findByFechaAfterAndTipoPago_Id(Date fechaInicio, Long tipoPagoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) <= DATE(:fechaFin) AND v.tipoPago.id = :tipoPagoId")
    List<Venta> findByFechaBeforeAndTipoPago_Id(Date fechaFin, Long tipoPagoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) >= DATE(:fechaInicio) AND v.tipoDocumento.id = :tipoDocumentoId")
    List<Venta> findByFechaAfterAndTipoDocumento_Id(Date fechaInicio, Long tipoDocumentoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) <= DATE(:fechaFin) AND v.tipoDocumento.id = :tipoDocumentoId")
    List<Venta> findByFechaBeforeAndTipoDocumento_Id(Date fechaFin, Long tipoDocumentoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin)")
    List<Venta> findByFechaBetween(Date fechaInicio, Date fechaFin);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) >= DATE(:fechaInicio) AND v.tipoPago.id = :tipoPagoId AND v.tipoDocumento.id = :tipoDocumentoId")
    List<Venta> findByTipoPago_IdAndTipoDocumento_IdAndFechaBetween(Long tipoPagoId, Long tipoDocumentoId, Date fechaInicio);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin) AND v.tipoDocumento.id = :tipoDocumentoId")
    List<Venta> findByFechaBetweenAndTipoDocumento_Id(Date fechaInicio, Date fechaFin, Long tipoDocumentoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin) AND v.tipoPago.id = :tipoPagoId")
    List<Venta> findByFechaAfterAndFechaBeforeAndTipoPago_IdAndTipoDocumento_Id(Date fechaInicio, Date fechaFin, Long tipoPagoId);

    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) <= DATE(:fechaFin) AND v.tipoPago.id = :tipoPagoId AND v.tipoDocumento.id = :tipoDocumentoId")
    List<Venta> findByFechaBeforeAndFechaAfterAndTipoPago_IdAndTipoDocumento_Id(Date fechaFin, Long tipoPagoId, Long tipoDocumentoId);
}
