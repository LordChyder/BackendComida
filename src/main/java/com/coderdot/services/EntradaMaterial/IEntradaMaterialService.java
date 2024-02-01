package com.coderdot.services.EntradaMaterial;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.EntradaMaterial;

public interface IEntradaMaterialService {

    public List<EntradaMaterial> getAll();

    public Optional<EntradaMaterial> getById(@NonNull Long id);

    public boolean create(@NonNull EntradaMaterial entity);

    public boolean update(@NonNull Long id,  EntradaMaterial entity);

    public boolean delete(@NonNull Long id);
} 