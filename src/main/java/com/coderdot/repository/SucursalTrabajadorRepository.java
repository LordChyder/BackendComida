package com.coderdot.repository;

import com.coderdot.entities.SucursalTrabajador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalTrabajadorRepository extends JpaRepository<SucursalTrabajador, Long> {
    List<SucursalTrabajador> findBySucursalId(Long sucursalId);
    List<SucursalTrabajador> findByUser_Username(String username);
}
