package com.coderdot.services.InventarioProducto;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.InventarioProducto;

public interface IInventarioProductoService {

    public List<InventarioProducto> getAll();

    public Optional<InventarioProducto> getById(@NonNull Long id);

    public boolean create(@NonNull InventarioProducto entity);

    public boolean update(@NonNull Long id,  InventarioProducto entity);

    public boolean delete(@NonNull Long id);
} 