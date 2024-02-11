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

    List<Compra> findByEntradaFalseAndEstadoTrueAndInventarioSucursalId(Long sucursalId);

    @Modifying
    @Query("UPDATE Compra c SET c.entrada = true WHERE c.id = :compraId")
    void actualizarEntradaATrue(@Param("compraId") Long compraId);
    
    @Modifying
    @Query("UPDATE Compra c SET c.entrada = false  WHERE c.id = :compraId")
    void actualizarEntradaAFalse(@Param("compraId") Long compraId);

    List<Compra> findByInventarioSucursalId(Long sucursalId);



    List<Compra> findByInventario_Id(Long inventarioId);
    
    List<Compra> findByProveedor_Id(Long proveedorId);
    
    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) > DATE(:fechaInicio)")
    List<Compra> findByFechaAfter(Date fechaInicio);
    
    
    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) < DATE(:fechaFin)")
    List<Compra> findByFechaBefore(Date fechaFin);
    
    List<Compra> findByInventario_IdAndProveedor_Id(Long inventarioId, Long proveedorId);
    

    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) > DATE(:fechaInicio) AND c.inventario.id = :inventarioId")
    List<Compra> findByFechaAfterAndInventario_Id(Date fechaInicio, Long inventarioId);

    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) < DATE(:fechaFin) AND c.inventario.id = :inventarioId")
    List<Compra> findByFechaBeforeAndInventario_Id(Date fechaFin, Long inventarioId);

    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) > DATE(:fechaInicio) AND c.proveedor.id = :proveedorId")
    List<Compra> findByFechaAfterAndProveedor_Id(Date fechaInicio, Long proveedorId);

    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) < DATE(:fechaFin) AND c.proveedor.id = :proveedorId")
    List<Compra> findByFechaBeforeAndProveedor_Id(Date fechaFin, Long proveedorId);

    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin)")
    List<Compra> findByFechaBetween(Date fechaInicio, Date fechaFin);
    
    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) > DATE(:fechaInicio) AND c.inventario.id = :inventarioId AND c.proveedor.id = :proveedorId")
    List<Compra> findByFechaAfterAndFechaAndInventario_IdAndProveedor_Id(Date fechaInicio, Long inventarioId, Long proveedorId);
    
    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin) AND c.inventario.id = :inventarioId")
    List<Compra> findByFechaBetweenAndInventario_Id(Date fechaInicio, Date fechaFin, Long inventarioId);
    
    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) < DATE(:fechaFin) AND c.inventario.id = :inventarioId AND c.proveedor.id = :proveedorId")
    List<Compra> findByFechaBeforeAndFechaAndInventario_IdAndProveedor_Id(Date fechaFin, Long inventarioId, Long proveedorId);

    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin) AND c.proveedor.id = :proveedorId")
    List<Compra> findByFechaBetweenAndProveedor_Id(Date fechaInicio, Date fechaFin, Long proveedorId);
    
    @Query("SELECT c FROM Compra c WHERE DATE(c.fecha) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin) AND c.proveedor.id = :proveedorId AND c.inventario.id = :inventarioId")
    List<Compra> findByFechaBetweenrAndInventario_IdAndProveedor_Id(Date fechaInicio, Date fechaFin, Long inventarioId, Long proveedorId);
}