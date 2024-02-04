package com.coderdot.repository;

import com.coderdot.entities.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    @Modifying
    @Query("UPDATE Pedido c SET c.estado = true WHERE c.id = :pedidoId")
    void actualizarEstado(@Param("pedidoId") Long pedidoId);
}
