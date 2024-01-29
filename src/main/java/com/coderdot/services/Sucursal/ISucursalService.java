package com.coderdot.services.Sucursal;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Sucursal;

public interface ISucursalService {

    public List<Sucursal> getAll();

    public Optional<Sucursal> getById(@NonNull Long id);

    public boolean create(@NonNull Sucursal entity);

    public boolean update(@NonNull Long id,  Sucursal entity);

    public boolean delete(@NonNull Long id);
} 