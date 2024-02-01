package com.coderdot.services.Perfil;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Perfil;
import com.coderdot.repository.PerfilRepository;

@Service
public class PerfilService implements IPerfilService {

    private final PerfilRepository _repository;

    public PerfilService(PerfilRepository repository) {
        this._repository = repository;
    }

    public List<Perfil> getAll() {
        return _repository.findAll();
    }

    public Optional<Perfil> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Perfil entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Perfil entity) {

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