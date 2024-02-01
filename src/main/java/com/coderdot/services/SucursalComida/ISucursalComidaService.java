package com.coderdot.services.SucursalComida;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.SucursalComida;

public interface ISucursalComidaService {

    public List<SucursalComida> getAll();

    public Optional<SucursalComida> getById(@NonNull Long id);

    public boolean create(@NonNull SucursalComida entity);

    public boolean update(@NonNull Long id,  SucursalComida entity);

    public boolean delete(@NonNull Long id);
} 