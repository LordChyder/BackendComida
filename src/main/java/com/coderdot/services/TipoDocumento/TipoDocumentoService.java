package com.coderdot.services.TipoDocumento;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.TipoDocumento;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService implements ITipoDocumentoService {

    private final TipoDocumentoRepository _repository;
    private final MessageResult _messageResult;

    public TipoDocumentoService(TipoDocumentoRepository repository, 
    MessageResult messageResult) {
        this._repository = repository;
        this._messageResult = messageResult;
    }

    public List<TipoDocumento> getAll() {
        return _repository.findAll();
    }

    public Optional<TipoDocumento> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull TipoDocumento entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  TipoDocumento entity) {

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setNombre(entity.getNombre());
                existingEntity.setEstado(entity.getEstado());
                return _repository.save(existingEntity);
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(@NonNull Long id) {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}