package com.coderdot.services.Caja;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Caja;

public interface ICajaService {

    public List<Caja> getAll();

    public Optional<Caja> getById(@NonNull Long id);

    public boolean create(@NonNull Caja entity);

    public boolean update(@NonNull Long id,  Caja entity);

    public boolean delete(@NonNull Long id);
} 