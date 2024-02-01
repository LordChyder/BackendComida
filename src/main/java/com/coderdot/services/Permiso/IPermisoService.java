package com.coderdot.services.Permiso;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Permiso;

public interface IPermisoService {

    public List<Permiso> getAll();

    public Optional<Permiso> getById(@NonNull Long id);

    public boolean create(@NonNull Permiso entity);

    public boolean update(@NonNull Long id,  Permiso entity);

    public boolean delete(@NonNull Long id);
} 