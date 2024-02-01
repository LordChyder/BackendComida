package com.coderdot.services.Perfil;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Perfil;

public interface IPerfilService {

    public List<Perfil> getAll();

    public Optional<Perfil> getById(@NonNull Long id);

    public boolean create(@NonNull Perfil entity);

    public boolean update(@NonNull Long id,  Perfil entity);

    public boolean delete(@NonNull Long id);
} 