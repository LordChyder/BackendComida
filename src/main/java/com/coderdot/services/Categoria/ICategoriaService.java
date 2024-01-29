package com.coderdot.services.Categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Categoria;

public interface ICategoriaService {

    public List<Categoria> getAll();

    public Optional<Categoria> getById(@NonNull Long id);

    public boolean create(@NonNull Categoria entity);

    public boolean update(@NonNull Long id,  Categoria entity);

    public boolean delete(@NonNull Long id);
} 