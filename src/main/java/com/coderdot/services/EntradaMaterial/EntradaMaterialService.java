package com.coderdot.services.EntradaMaterial;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Compra;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.repository.EntradaMaterialRepository;
import com.coderdot.repository.InventarioRepository;
import com.coderdot.repository.CompraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EntradaMaterialService implements IEntradaMaterialService {

    private final EntradaMaterialRepository _repository;
    private final CompraRepository _compraRepository;
    private final InventarioRepository _inventarioRepository;
    
    public EntradaMaterialService(EntradaMaterialRepository repository, CompraRepository compraRepository, InventarioRepository inventarioRepository) {
        this._repository = repository;
        this._compraRepository = compraRepository;
        this._inventarioRepository = inventarioRepository;
    }

    public List<EntradaMaterial> getAll() {
        return _repository.findAll();
    }

    public Optional<EntradaMaterial> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull EntradaMaterial entity) {

        try {
            Long producto_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;
    
            Compra producto = _compraRepository.findById(producto_id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +producto_id));

            Long inventario_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;
    
            Inventario inventario = _inventarioRepository.findById(inventario_id)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " +inventario_id));

            entity.setCompra(producto);
            entity.setInventario(inventario);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  EntradaMaterial entity) {
        Long producto_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;

        Compra producto = _compraRepository.findById(producto_id)
            .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +producto_id));

        Long inventario_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;

        Inventario inventario = _inventarioRepository.findById(inventario_id)
            .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " +inventario_id));

        entity.setCompra(producto);
        entity.setInventario(inventario);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setCompra(entity.getCompra());
                existingEntity.setInventario(entity.getInventario());
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