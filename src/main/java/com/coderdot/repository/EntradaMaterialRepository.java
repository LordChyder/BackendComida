package com.coderdot.repository;

import com.coderdot.entities.EntradaMaterial;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaMaterialRepository extends JpaRepository<EntradaMaterial, Long> {

    @Query("SELECT em FROM EntradaMaterial em WHERE DATE(em.fecha) = DATE(:fecha) AND DATE(em.fechaVencimiento) = DATE(:fechaVencimiento) AND compra.inventario.id = :inventarioId")
    List<EntradaMaterial> findByFechaAndFechaVencimientoAndCompra_Inventario_Id(Date fecha, Date fechaVencimiento, Long inventarioId);

    @Query("SELECT em FROM EntradaMaterial em WHERE DATE(em.fecha) = DATE(:fecha) AND compra.inventario.id = :inventarioId")
    List<EntradaMaterial> findByFechaAndCompra_Inventario_Id(Date fecha, Long inventarioId);

    @Query("SELECT em FROM EntradaMaterial em WHERE DATE(em.fechaVencimiento) = DATE(:fechaVencimiento) AND compra.inventario.id = :inventarioId")
    List<EntradaMaterial> findByFechaVencimientoAndCompra_Inventario_Id(Date fechaVencimiento, Long inventarioId);

    @Query("SELECT em FROM EntradaMaterial em WHERE DATE(em.fecha) = DATE(:fecha) AND DATE(em.fechaVencimiento) = DATE(:fechaVencimiento)")
    List<EntradaMaterial> findByFechaVencimientoAndFecha(Date fechaVencimiento, Date fecha);

    List<EntradaMaterial> findByCompra_Inventario_Id(Long inventarioId);

    @Query("SELECT em FROM EntradaMaterial em WHERE DATE(em.fecha) = DATE(:fecha)")
    List<EntradaMaterial> findByFecha(Date fecha);

    @Query("SELECT em FROM EntradaMaterial em WHERE DATE(em.fechaVencimiento) = DATE(:fechaVencimiento)")
    List<EntradaMaterial> findByFechaVencimiento(Date fechaVencimiento);

    List<EntradaMaterial> findByCompraInventarioSucursalId(Long sucursalId);
}
