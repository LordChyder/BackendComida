package com.coderdot.services.TipoDocumento;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import com.coderdot.entities.TipoDocumento;

public interface ITipoDocumentoService {

    public List<TipoDocumento> getAll();

    public Optional<TipoDocumento> getById(@NonNull Long id);

    public boolean create(@NonNull TipoDocumento entity);

    public boolean update(@NonNull Long id,  TipoDocumento entity);

    public boolean delete(@NonNull Long id);
} 