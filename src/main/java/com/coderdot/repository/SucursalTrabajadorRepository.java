package com.coderdot.repository;

import com.coderdot.entities.SucursalTrabajador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalTrabajadorRepository extends JpaRepository<SucursalTrabajador, Long> {
}
