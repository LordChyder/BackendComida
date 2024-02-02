package com.coderdot.services.VentaDetalle;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.VentaDetalle;

public interface IVentaDetalleService {

    public List<VentaDetalle> getAll();

    public Optional<VentaDetalle> getById(@NonNull Long id);

    public boolean create(@NonNull VentaDetalle entity);

    public boolean update(@NonNull Long id,  VentaDetalle entity);

    public boolean delete(@NonNull Long id);
} 