package com.coderdot.repository;

import com.coderdot.entities.CajaApertura;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaAperturaRepository extends JpaRepository<CajaApertura, Long> {
    List<CajaApertura> findByCerradoFalseAndCajaSucursalId(Long sucursalId);

    List<CajaApertura> findByCerradoFalseAndCajaSucursalIdAndUserUsername(Long sucursalId, String username);

    
    @Query("SELECT ca FROM CajaApertura ca " +
           "WHERE ca.cerrado = false " +
           "AND ca.caja.sucursal.id = :sucursalId " +
           "AND FUNCTION('YEAR', ca.fecha) = FUNCTION('YEAR', :fecha) " +
           "AND FUNCTION('MONTH', ca.fecha) = FUNCTION('MONTH', :fecha) " +
           "AND FUNCTION('DAY', ca.fecha) = FUNCTION('DAY', :fecha) " +
           "AND ca.user.username = :username")
    List<CajaApertura> findByCerradoFalseAndCajaSucursalIdAndFechaAndUserUsername(
            @Param("sucursalId") Long sucursalId,
            @Param("fecha") Date fecha,
            @Param("username") String username
    );

    @Modifying
    @Query("UPDATE CajaApertura c SET c.cerrado = true WHERE c.id = :cajaId")
    void actualizarEstado(@Param("cajaId") Long cajaId);
}
