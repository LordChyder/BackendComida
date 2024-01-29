package com.coderdot.services.TipoPago;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.TipoPago;

public interface ITipoPagoService {

    public List<TipoPago> getAll();

    public Optional<TipoPago> getById(@NonNull Long id);

    public boolean create(@NonNull TipoPago entity);

    public boolean update(@NonNull Long id,  TipoPago entity);

    public boolean delete(@NonNull Long id);
} 