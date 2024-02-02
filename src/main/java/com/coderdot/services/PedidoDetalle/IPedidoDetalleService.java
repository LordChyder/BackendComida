package com.coderdot.services.PedidoDetalle;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.PedidoDetalle;

public interface IPedidoDetalleService {

    public List<PedidoDetalle> getAll();

    public Optional<PedidoDetalle> getById(@NonNull Long id);

    public boolean create(@NonNull PedidoDetalle entity);

    public boolean update(@NonNull Long id,  PedidoDetalle entity);

    public boolean delete(@NonNull Long id);
} 