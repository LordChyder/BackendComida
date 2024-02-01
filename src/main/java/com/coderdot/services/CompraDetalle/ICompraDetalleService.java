package com.coderdot.services.CompraDetalle;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.CompraDetalle;

public interface ICompraDetalleService {

    public List<CompraDetalle> getAll();

    public Optional<CompraDetalle> getById(@NonNull Long id);

    public boolean create(@NonNull CompraDetalle entity);

    public boolean update(@NonNull Long id,  CompraDetalle entity);

    public boolean delete(@NonNull Long id);
} 