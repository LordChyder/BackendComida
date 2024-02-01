package com.coderdot.services.Permiso;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Permiso;
import com.coderdot.repository.PermisoRepository;

@Service
public class PermisoService implements IPermisoService {

    private final PermisoRepository _repository;

    public PermisoService(PermisoRepository repository) {
        this._repository = repository;
    }

    public List<Permiso> getAll() {
        return _repository.findAll();
    }

    public Optional<Permiso> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Permiso entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Permiso entity) {

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setNombre(entity.getNombre());
                existingEntity.setCodigo(entity.getCodigo());
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
}