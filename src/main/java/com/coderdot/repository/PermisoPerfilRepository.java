package com.coderdot.repository;

import com.coderdot.entities.PermisoPerfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoPerfilRepository extends JpaRepository<PermisoPerfil, Long> {
}
