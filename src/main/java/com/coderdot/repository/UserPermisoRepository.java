package com.coderdot.repository;

import com.coderdot.entities.Permiso;
import com.coderdot.entities.UserPermiso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserPermisoRepository extends JpaRepository<UserPermiso, Long> {
    @Query("SELECT pp.permiso FROM UserPermiso pp WHERE pp.user.id = :userId")
    List<Permiso> findPermisosByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Permiso p WHERE p.id NOT IN (SELECT pp.permiso.id FROM UserPermiso pp WHERE pp.user.id = :userId)")
    List<Permiso> findPermisosNotInUser(@Param("userId") Long userId);
    
    @Transactional
    void deleteByPermisoIdAndUserId(Long permisoId, Long userId);
}
