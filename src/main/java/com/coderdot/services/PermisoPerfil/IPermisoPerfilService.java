package com.coderdot.services.PermisoPerfil;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.PermisoPerfil;

public interface IPermisoPerfilService {

    public List<PermisoPerfil> getAll();

    public Optional<PermisoPerfil> getById(@NonNull Long id);

    public boolean create(@NonNull PermisoPerfil entity);

    public boolean update(@NonNull Long id,  PermisoPerfil entity);

    public boolean delete(@NonNull Long id);
} 