package com.coderdot.services.UserPermiso;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Permiso;
import com.coderdot.entities.UserPermiso;

public interface IUserPermisoService {

    public List<UserPermiso> getAll();

    public Optional<UserPermiso> getById(@NonNull Long id);

    public boolean create(@NonNull UserPermiso entity);

    public boolean update(@NonNull Long id,  UserPermiso entity);

    public boolean delete(@NonNull Long id);

    public List<Permiso> getPermisosByUserId(Long userId);

    public List<Permiso> getPermisosNotInUser(Long userId);

    public boolean deleteByPermisoAndUser(@NonNull Long permisoId, @NonNull Long userId);
} 