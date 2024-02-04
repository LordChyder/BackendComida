package com.coderdot.repository;

import com.coderdot.entities.Inventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
    @Query("SELECT i FROM Inventario i WHERE i.sucursal.id = :sucursalId")
    List<Inventario> findBySucursalId(Long sucursalId);
}
