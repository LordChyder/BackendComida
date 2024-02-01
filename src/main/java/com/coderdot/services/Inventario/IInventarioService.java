package com.coderdot.services.Inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Inventario;

public interface IInventarioService {

    public List<Inventario> getAll();

    public Optional<Inventario> getById(@NonNull Long id);

    public boolean create(@NonNull Inventario entity);

    public boolean update(@NonNull Long id,  Inventario entity);

    public boolean delete(@NonNull Long id);
} 