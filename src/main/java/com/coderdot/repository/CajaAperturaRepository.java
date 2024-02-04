package com.coderdot.repository;

import com.coderdot.entities.CajaApertura;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaAperturaRepository extends JpaRepository<CajaApertura, Long> {
    List<CajaApertura> findByCerradoFalseAndCajaSucursalId(Long sucursalId);
}
