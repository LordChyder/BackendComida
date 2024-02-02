package com.coderdot.services.Venta;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Venta;

public interface IVentaService {

    public List<Venta> getAll();

    public Optional<Venta> getById(@NonNull Long id);

    public boolean create(@NonNull Venta entity);

    public boolean update(@NonNull Long id,  Venta entity);

    public boolean delete(@NonNull Long id);
} 