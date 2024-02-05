package com.coderdot.repository;

import com.coderdot.entities.EntradaMaterial;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaMaterialRepository extends JpaRepository<EntradaMaterial, Long> {
    List<EntradaMaterial> findByFechaAndCompra_Inventario_Id(Date fecha, Long inventarioId);

    List<EntradaMaterial> findByCompra_Inventario_Id(Long inventarioId);

    List<EntradaMaterial> findByFecha(Date fecha);
}
