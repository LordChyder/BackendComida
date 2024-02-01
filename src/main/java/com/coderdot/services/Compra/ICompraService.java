package com.coderdot.services.Compra;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Compra;

public interface ICompraService {

    public List<Compra> getAll();

    public Optional<Compra> getById(@NonNull Long id);

    public boolean create(@NonNull Compra entity);

    public boolean update(@NonNull Long id,  Compra entity);

    public boolean delete(@NonNull Long id);
} 