package com.coderdot.repository;

import com.coderdot.entities.Comida;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long> {
    @Query("SELECT c FROM Comida c WHERE c.id NOT IN (SELECT sc.comida.id FROM SucursalComida sc WHERE sc.sucursal.id = :sucursalId)")
    List<Comida> findComidasNotInSucursal(Long sucursalId);
}
