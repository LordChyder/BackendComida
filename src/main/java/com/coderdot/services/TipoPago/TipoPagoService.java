package com.coderdot.services.TipoPago;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.TipoPago;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.TipoPagoRepository;

@Service
public class TipoPagoService implements ITipoPagoService {

    private final TipoPagoRepository _repository;
    private final MessageResult _messageResult;

    public TipoPagoService(TipoPagoRepository repository, 
    MessageResult messageResult) {
        this._repository = repository;
        this._messageResult = messageResult;
    }

    public List<TipoPago> getAll() {
        return _repository.findAll();
    }

    public Optional<TipoPago> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull TipoPago entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  TipoPago entity) {

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

    public List<TipoPago> obtenerActivos() {
        return _repository.findByEstadoTrue();
    }
}