package com.coderdot.repository;

import com.coderdot.entities.PerfilUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUserRepository extends JpaRepository<PerfilUser, Long> {
}
