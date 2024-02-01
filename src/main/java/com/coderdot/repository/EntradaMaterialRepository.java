package com.coderdot.repository;

import com.coderdot.entities.EntradaMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaMaterialRepository extends JpaRepository<EntradaMaterial, Long> {
}
