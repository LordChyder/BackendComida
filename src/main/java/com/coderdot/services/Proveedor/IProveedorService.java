package com.coderdot.services.Proveedor;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Proveedor;

public interface IProveedorService {

    public List<Proveedor> getAll();

    public Optional<Proveedor> getById(@NonNull Long id);

    public boolean create(@NonNull Proveedor entity);

    public boolean update(@NonNull Long id,  Proveedor entity);

    public boolean delete(@NonNull Long id);
} 