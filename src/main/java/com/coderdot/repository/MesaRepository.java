package com.coderdot.repository;

import com.coderdot.entities.Mesa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findBySucursalId(Long sucursalId);
}
