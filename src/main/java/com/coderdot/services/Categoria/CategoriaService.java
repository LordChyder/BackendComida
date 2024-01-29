package com.coderdot.services.Categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Categoria;
import com.coderdot.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository _repository;

    public CategoriaService(CategoriaRepository repository) {
        this._repository = repository;
    }

    public List<Categoria> getAll() {
        return _repository.findAll();
    }

    public Optional<Categoria> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Categoria entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Categoria entity) {

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setNombre(entity.getNombre());
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