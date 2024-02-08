package com.coderdot.services.Proveedor;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Proveedor;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.ProveedorRepository;

@Service
public class ProveedorService implements IProveedorService {

    private final ProveedorRepository _repository;
    private final MessageResult _messageResult;

    public ProveedorService(ProveedorRepository repository, MessageResult messageResult) {
        this._repository = repository;
        this._messageResult = messageResult;
    }

    public List<Proveedor> getAll() {
        return _repository.findAll();
    }

    public Optional<Proveedor> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Proveedor entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Proveedor entity) {

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setEmpresa(entity.getEmpresa());
                existingEntity.setDireccion(entity.getDireccion());
                existingEntity.setRuc(entity.getRuc());
                existingEntity.setRepresentante(entity.getRepresentante());
                existingEntity.setTelefono(entity.getTelefono());
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

    public List<Proveedor> obtenerProveedoresActivos() {
        return _repository.findByEstadoTrue();
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}