package com.coderdot.repository;

import com.coderdot.entities.Compra;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    
    @Modifying
    @Query("UPDATE Compra c SET c.estado = true WHERE c.id = :compraId")
    void actualizarEstado(@Param("compraId") Long compraId);

    List<Compra> findByEntradaFalseAndEstadoTrue();

    @Modifying
    @Query("UPDATE Compra c SET c.entrada = true WHERE c.id = :compraId")
    void actualizarEntradaATrue(@Param("compraId") Long compraId);

    List<Compra> findByInventarioSucursalId(Long sucursalId);


    
    List<Compra> findByInventario_Id(Long inventarioId);

    List<Compra> findByProveedor_Id(Long proveedorId);

    List<Compra> findByUser_Id(Long userId);

    List<Compra> findByFecha(Date fecha);

    List<Compra> findByFechaAndInventario_Id(Date fecha, Long inventarioId);

    List<Compra> findByFechaAndProveedor_Id(Date fecha, Long proveedorId);

    List<Compra> findByFechaAndUser_Id(Date fecha, Long userId);

    List<Compra> findByInventario_IdAndProveedor_Id(Long inventarioId, Long proveedorId);

    List<Compra> findByInventario_IdAndUser_Id(Long inventarioId, Long userId);

    List<Compra> findByProveedor_IdAndUser_Id(Long proveedorId, Long userId);

    List<Compra> findByFechaAndInventario_IdAndProveedor_Id(Date fecha, Long inventarioId, Long proveedorId);

    List<Compra> findByFechaAndInventario_IdAndUser_Id(Date fecha, Long inventarioId, Long userId);

    List<Compra> findByFechaAndProveedor_IdAndUser_Id(Date fecha, Long proveedorId, Long userId);

    List<Compra> findByFechaAndInventario_IdAndProveedor_IdAndUser_Id(
            Date fecha, Long inventarioId, Long proveedorId, Long userId);
}