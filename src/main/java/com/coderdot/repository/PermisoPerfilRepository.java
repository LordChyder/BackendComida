package com.coderdot.repository;

import com.coderdot.entities.Permiso;
import com.coderdot.entities.PermisoPerfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PermisoPerfilRepository extends JpaRepository<PermisoPerfil, Long> {
    @Query("SELECT pp.permiso FROM PermisoPerfil pp WHERE pp.perfil.id = :perfilId")
    List<Permiso> findPermisosByPerfilId(@Param("perfilId") Long perfilId);

    @Query("SELECT p FROM Permiso p WHERE p.id NOT IN (SELECT pp.permiso.id FROM PermisoPerfil pp WHERE pp.perfil.id = :perfilId)")
    List<Permiso> findPermisosNotInPerfil(@Param("perfilId") Long perfilId);
    
    @Transactional
    void deleteByPermisoIdAndPerfilId(Long permisoId, Long perfilId);
}
