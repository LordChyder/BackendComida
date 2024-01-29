package com.coderdot.services.Establecimiento;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Establecimiento;

public interface IEstablecimientoService {

    public List<Establecimiento> getAll();

    public Optional<Establecimiento> getById(@NonNull Long id);

    public boolean create(@NonNull Establecimiento entity);

    public boolean update(@NonNull Long id,  Establecimiento entity);

    public boolean delete(@NonNull Long id);
} 