package com.coderdot.repository;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.PerfilUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PerfilUserRepository extends JpaRepository<PerfilUser, Long> {

    @Query("SELECT pu.perfil FROM PerfilUser pu WHERE pu.user.id = :userId")
    List<Perfil> findPerfilesByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Perfil p WHERE p.id NOT IN (SELECT pu.perfil.id FROM PerfilUser pu WHERE pu.user.id = :userId)")
    List<Perfil> findPerfilesNotInUser(@Param("userId") Long userId);
    
    @Transactional
    void deleteByUserIdAndPerfilId(Long userId, Long perfilId);
}
