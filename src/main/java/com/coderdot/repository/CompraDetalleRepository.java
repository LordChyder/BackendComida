package com.coderdot.repository;

import com.coderdot.entities.CompraDetalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long> {
}
