package com.coderdot.repository;

import com.coderdot.entities.VentaDetalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
    
    @Query("SELECT cd FROM VentaDetalle cd WHERE cd.venta.id = :ventaId")
    List<VentaDetalle> findByVentaId(@Param("ventaId") Long ventaId);
    
    @Query("SELECT cd FROM VentaDetalle cd WHERE cd.id = :detalleId")
    VentaDetalle findYesById(@Param("detalleId") Long detalleId);
}
