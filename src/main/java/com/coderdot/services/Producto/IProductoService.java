package com.coderdot.services.Producto;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Producto;

public interface IProductoService {

    public List<Producto> getAll();

    public Optional<Producto> getById(@NonNull Long id);

    public boolean create(Producto entity);

    public boolean update(@NonNull Long id,  Producto entity);

    public boolean delete(@NonNull Long id);
} 