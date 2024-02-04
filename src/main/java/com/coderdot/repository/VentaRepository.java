package com.coderdot.repository;

import com.coderdot.entities.Venta;

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

    
    @Query("SELECT cd FROM Venta cd WHERE cd.id = :ventaId")
    Venta findYesById(@Param("ventaId") Long ventaId);
}
