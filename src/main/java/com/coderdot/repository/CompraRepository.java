package com.coderdot.repository;

import com.coderdot.entities.Compra;

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
}
