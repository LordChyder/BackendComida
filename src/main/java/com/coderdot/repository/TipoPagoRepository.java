package com.coderdot.repository;

import com.coderdot.entities.TipoPago;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
    List<TipoPago> findByEstadoTrue();
}
