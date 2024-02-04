package com.coderdot.services.PerfilUser;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.PerfilUser;

public interface IPerfilUserService {

    public List<PerfilUser> getAll();

    public Optional<PerfilUser> getById(@NonNull Long id);

    public boolean create(@NonNull PerfilUser entity);

    public boolean update(@NonNull Long id,  PerfilUser entity);

    public boolean delete(@NonNull Long id);

    public List<Perfil> getPerfilesByUserId(Long userId);

    public List<Perfil> getPerfilesNotInUser(Long userId);

    public boolean deleteByUserAndPerfil(@NonNull Long userId, @NonNull Long perfilId);
} 