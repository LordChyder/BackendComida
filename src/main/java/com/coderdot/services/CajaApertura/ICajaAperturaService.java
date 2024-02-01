package com.coderdot.services.CajaApertura;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.CajaApertura;

public interface ICajaAperturaService {

    public List<CajaApertura> getAll();

    public Optional<CajaApertura> getById(@NonNull Long id);

    public boolean create(@NonNull CajaApertura entity);

    public boolean update(@NonNull Long id,  CajaApertura entity);

    public boolean delete(@NonNull Long id);
} 