package com.coderdot.repository;

import com.coderdot.entities.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
