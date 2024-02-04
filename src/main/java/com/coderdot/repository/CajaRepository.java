package com.coderdot.repository;

import com.coderdot.entities.Caja;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Long> {
    List<Caja> findBySucursalId(Long sucursalId);
}
