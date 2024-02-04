package com.coderdot.services.CompraDetalle;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Compra;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.Producto;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.CompraDetalleRepository;
import com.coderdot.repository.ProductoRepository;
import com.coderdot.repository.CompraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompraDetalleService implements ICompraDetalleService {

    private final CompraDetalleRepository _repository;
    private final CompraRepository _compraRepository;
    private final ProductoRepository _productoRepository;
    private final MessageResult _messageResult;
    
    public CompraDetalleService(CompraDetalleRepository repository, CompraRepository compraRepository, ProductoRepository productoRepository, 
        MessageResult messageResult) {
        this._repository = repository;
        this._compraRepository = compraRepository;
        this._productoRepository = productoRepository;
        this._messageResult = messageResult;
    }

    public List<CompraDetalle> getAll() {
        return _repository.findAll();
    }

    public Optional<CompraDetalle> getById(@NonNull Long id) {
        return _repository.findById(id);
    }
    

    public List<CompraDetalle> getDetallePorCompra(Long compraId) {
        return _repository.findByCompraId(compraId);
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

            entity.setTotal(entity.getPrecio_unitario() * entity.getUnidad());

            _repository.save(entity);

            List<CompraDetalle> detalles = _repository.findByCompraId(compra_id);
            double sumaTotales = 0.0;

            for (CompraDetalle detalle : detalles) {
                sumaTotales += detalle.getTotal();
            }

            compra.setTotal(sumaTotales);

            _compraRepository.save(compra);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  CompraDetalle entity) {
        Long compra_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;

        Compra compra = _compraRepository.findById(compra_id)
            .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +compra_id));

        Long producto_id = entity.getProducto().getId() != null ? entity.getProducto().getId() : 0;

        Producto producto = _productoRepository.findById(producto_id)
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrada con id: " +producto_id));

        entity.setCompra(compra);
        entity.setProducto(producto);
        entity.setTotal(entity.getPrecio_unitario() * entity.getUnidad());

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setPrecio_unitario(entity.getPrecio_unitario());
                existingEntity.setUnidad(entity.getUnidad());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setCompra(entity.getCompra());
                existingEntity.setProducto(entity.getProducto());
                return _repository.save(existingEntity);
            });

            List<CompraDetalle> detalles = _repository.findByCompraId(compra_id);
            double sumaTotales = 0.0;

            for (CompraDetalle detalle : detalles) {
                sumaTotales += detalle.getTotal();
            }

            compra.setTotal(sumaTotales);

            _compraRepository.save(compra);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(@NonNull Long id) {
        if (_repository.existsById(id)) {
            CompraDetalle detail = _repository.findYesById(id);
            Long compra_id = detail.getCompra().getId() != null ? detail.getCompra().getId() : 0;
            
            _repository.deleteById(id);

            
    
            Compra compra = _compraRepository.findById(compra_id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +compra_id));


            List<CompraDetalle> detalles = _repository.findByCompraId(compra_id);
            double sumaTotales = 0.0;

            for (CompraDetalle detalle : detalles) {
                sumaTotales += detalle.getTotal();
            }

            compra.setTotal(sumaTotales);

            _compraRepository.save(compra);

            return true;
        } else {
            return false;
        }
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}