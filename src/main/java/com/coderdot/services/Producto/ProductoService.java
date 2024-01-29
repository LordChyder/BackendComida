package com.coderdot.services.Producto;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Categoria;
import com.coderdot.entities.Producto;
import com.coderdot.repository.CategoriaRepository;
import com.coderdot.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService implements IProductoService {

    private final ProductoRepository _repository;
    private final CategoriaRepository _categoriaRepository;

    public ProductoService(ProductoRepository repository, CategoriaRepository categoriaRepository) {
        this._repository = repository;
        this._categoriaRepository = categoriaRepository;
    }

    public List<Producto> getAll() {
        return _repository.findAll();
    }

    public Optional<Producto> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(Producto entity) {
        try {

            Long categoriaId = entity.getCategoria().getId() != null ? entity.getCategoria().getId() : 0;

            Categoria categoria = _categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " +categoriaId));

            entity.setCategoria(categoria);
        
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Producto entity) {
        Long categoriaId = entity.getCategoria().getId() != null ? entity.getCategoria().getId() : 0;

        Categoria categoria = _categoriaRepository.findById(categoriaId)
        .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " +categoriaId));

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setNombre(entity.getNombre());
                existingEntity.setDescripcion(entity.getDescripcion());
                existingEntity.setCaracteristica(entity.getCaracteristica());
                existingEntity.setCodigo(entity.getCodigo());
                existingEntity.setCategoria(categoria);
    
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