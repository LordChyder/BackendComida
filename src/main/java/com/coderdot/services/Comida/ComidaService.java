package com.coderdot.services.Comida;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Comida;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.ComidaRepository;

@Service
public class ComidaService implements IComidaService {

    private final ComidaRepository _repository;
    private final MessageResult _messageResult;

    public ComidaService(ComidaRepository repository, 
    MessageResult messageResult) {
        this._repository = repository;
        this._messageResult = messageResult;
    }

    public List<Comida> getAll() {
        return _repository.findAll();
    }

    public Optional<Comida> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Comida entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Comida entity) {

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setNombre(entity.getNombre());
                existingEntity.setCodigo(entity.getCodigo());
                existingEntity.setImagen(entity.getImagen());
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