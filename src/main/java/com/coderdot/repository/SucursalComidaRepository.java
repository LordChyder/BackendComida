package com.coderdot.repository;

import com.coderdot.entities.SucursalComida;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalComidaRepository extends JpaRepository<SucursalComida, Long> {
    List<SucursalComida> findBySucursalId(Long sucursalId);
}
