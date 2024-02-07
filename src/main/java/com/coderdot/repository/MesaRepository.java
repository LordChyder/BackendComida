package com.coderdot.repository;

import com.coderdot.dto.response.MesaPedidoDTO;
import com.coderdot.entities.Mesa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findBySucursalId(Long sucursalId);

    @Query("SELECT new com.coderdot.dto.response.MesaPedidoDTO(m, p) FROM Mesa m LEFT JOIN Pedido p " +
           "ON (p.mesa.id = m.id AND p.venta IS NULL AND p.anulado = false) " +
           "WHERE m.sucursal.id = ?1")
    List<MesaPedidoDTO> findBySucursalIdWithFilteredPedidos(Long sucursalId);
}
