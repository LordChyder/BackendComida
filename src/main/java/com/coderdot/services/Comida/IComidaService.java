package com.coderdot.services.Comida;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Comida;

public interface IComidaService {

    public List<Comida> getAll();

    public Optional<Comida> getById(@NonNull Long id);

    public boolean create(@NonNull Comida entity);

    public boolean update(@NonNull Long id,  Comida entity);

    public boolean delete(@NonNull Long id);
} 