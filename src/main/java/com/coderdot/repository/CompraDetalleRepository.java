package com.coderdot.repository;

import com.coderdot.entities.CompraDetalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long> {
    
    @Query("SELECT cd FROM CompraDetalle cd WHERE cd.compra.id = :compraId")
    List<CompraDetalle> findByCompraId(@Param("compraId") Long compraId);
    
    @Query("SELECT cd FROM CompraDetalle cd WHERE cd.id = :detalleId")
    CompraDetalle findYesById(@Param("detalleId") Long detalleId);
}
