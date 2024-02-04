package com.coderdot.repository;

import com.coderdot.entities.PedidoDetalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
    @Query("SELECT cd FROM PedidoDetalle cd WHERE cd.pedido.id = :pedidoId")
    List<PedidoDetalle> findByPedidoId(@Param("pedidoId") Long pedidoId);

    @Query("SELECT pd, sc.precio FROM PedidoDetalle pd " +
       "JOIN pd.comida c " +
       "JOIN SucursalComida sc ON sc.comida.id = c.id " +
       "WHERE pd.pedido.id = :pedidoId AND pd.pedido.sucursal.id = sc.sucursal.id")
    List<Object[]> findPedidoDetalleWithPrecioByPedidoId(@Param("pedidoId") Long pedidoId);
}
