package com.coderdot.services.InventarioProducto;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Producto;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.repository.InventarioProductoRepository;
import com.coderdot.repository.InventarioRepository;
import com.coderdot.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InventarioProductoService implements IInventarioProductoService {

    private final InventarioProductoRepository _repository;
    private final ProductoRepository _productoRepository;
    private final InventarioRepository _inventarioRepository;
    
    public InventarioProductoService(InventarioProductoRepository repository, ProductoRepository productoRepository, InventarioRepository inventarioRepository) {
        this._repository = repository;
        this._productoRepository = productoRepository;
        this._inventarioRepository = inventarioRepository;
    }

    public List<InventarioProducto> getAll() {
        return _repository.findAll();
    }

    public Optional<InventarioProducto> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull InventarioProducto entity) {

        try {
            Long producto_id = entity.getProducto().getId() != null ? entity.getProducto().getId() : 0;
    
            Producto producto = _productoRepository.findById(producto_id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrada con id: " +producto_id));

            Long inventario_id = entity.getProducto().getId() != null ? entity.getProducto().getId() : 0;
    
            Inventario inventario = _inventarioRepository.findById(inventario_id)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " +inventario_id));

            entity.setProducto(producto);
            entity.setInventario(inventario);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  InventarioProducto entity) {
        Long producto_id = entity.getProducto().getId() != null ? entity.getProducto().getId() : 0;

        Producto producto = _productoRepository.findById(producto_id)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrada con id: " +producto_id));

        Long inventario_id = entity.getProducto().getId() != null ? entity.getProducto().getId() : 0;

        Inventario inventario = _inventarioRepository.findById(inventario_id)
            .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " +inventario_id));

        entity.setProducto(producto);
        entity.setInventario(inventario);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setStock(entity.getStock());
                existingEntity.setProducto(producto);
                existingEntity.setInventario(inventario);
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