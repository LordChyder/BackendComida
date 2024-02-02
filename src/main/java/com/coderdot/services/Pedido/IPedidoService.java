package com.coderdot.services.Pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Pedido;

public interface IPedidoService {

    public List<Pedido> getAll();

    public Optional<Pedido> getById(@NonNull Long id);

    public boolean create(@NonNull Pedido entity);

    public boolean update(@NonNull Long id,  Pedido entity);

    public boolean delete(@NonNull Long id);
} 