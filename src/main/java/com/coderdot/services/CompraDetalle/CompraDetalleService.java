package com.coderdot.services.CompraDetalle;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Compra;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.Producto;
import com.coderdot.repository.CompraDetalleRepository;
import com.coderdot.repository.ProductoRepository;
import com.coderdot.repository.CompraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompraDetalleService implements ICompraDetalleService {

    private final CompraDetalleRepository _repository;
    private final CompraRepository _compraRepository;
    private final ProductoRepository _productoRepository;
    
    public CompraDetalleService(CompraDetalleRepository repository, CompraRepository compraRepository
    , ProductoRepository productoRepository) {
        this._repository = repository;
        this._compraRepository = compraRepository;
        this._productoRepository = productoRepository;
    }

    public List<CompraDetalle> getAll() {
        return _repository.findAll();
    }

    public Optional<CompraDetalle> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull CompraDetalle entity) {

        try {
            Long compra_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;
    
            Compra compra = _compraRepository.findById(compra_id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +compra_id));
    
            Long producto_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;
    
            Producto producto = _productoRepository.findById(producto_id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrada con id: " +producto_id));
    
            entity.setCompra(compra);
            entity.setProducto(producto);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  CompraDetalle entity) {
        Long compra_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;

        Compra compra = _compraRepository.findById(compra_id)
            .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +compra_id));

        Long producto_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;

        Producto producto = _productoRepository.findById(producto_id)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrada con id: " +producto_id));

        entity.setCompra(compra);
        entity.setProducto(producto);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setPrecio_unitario(entity.getPrecio_unitario());
                existingEntity.setUnidad(entity.getUnidad());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setCompra(entity.getCompra());
                existingEntity.setProducto(entity.getProducto());
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