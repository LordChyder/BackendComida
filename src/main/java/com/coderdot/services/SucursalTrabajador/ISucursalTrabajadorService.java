package com.coderdot.services.SucursalTrabajador;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.SucursalTrabajador;

public interface ISucursalTrabajadorService {

    public List<SucursalTrabajador> getAll();

    public Optional<SucursalTrabajador> getById(@NonNull Long id);

    public boolean create(@NonNull SucursalTrabajador entity);

    public boolean update(@NonNull Long id,  SucursalTrabajador entity);

    public boolean delete(@NonNull Long id);
} 