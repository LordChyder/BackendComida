package com.coderdot.repository;

import com.coderdot.entities.InventarioProducto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioProductoRepository extends JpaRepository<InventarioProducto, Long> {
}
