package com.coderdot.repository;

import com.coderdot.entities.InventarioProducto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioProductoRepository extends JpaRepository<InventarioProducto, Long> {
    List<InventarioProducto> findByInventarioId(Long inventarioId);

    Optional<InventarioProducto> findByProductoIdAndInventarioId(Long productoId, Long inventarioId);
}
