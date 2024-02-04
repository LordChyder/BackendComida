package com.coderdot.repository;

import com.coderdot.entities.Pedido;

import java.util.List;

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

    @Query("SELECT c FROM Pedido c WHERE c.sucursal.id = :sucursalId AND c.estado = true AND c.anulado = false AND c.venta IS NULL")
    List<Pedido> findBySucursalIdAndEstadoTrueAndAnuladoFalseAndVentaIsNull(@Param("sucursalId") Long sucursalId);
}
