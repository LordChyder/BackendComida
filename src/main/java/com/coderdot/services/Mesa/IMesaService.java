package com.coderdot.services.Mesa;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.Mesa;

public interface IMesaService {

    public List<Mesa> getAll();

    public Optional<Mesa> getById(@NonNull Long id);

    public boolean create(@NonNull Mesa entity);

    public boolean update(@NonNull Long id,  Mesa entity);

    public boolean delete(@NonNull Long id);
} 