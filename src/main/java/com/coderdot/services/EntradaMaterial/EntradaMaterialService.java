package com.coderdot.services.EntradaMaterial;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderdot.entities.Compra;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.EntradaMaterialRepository;
import com.coderdot.repository.InventarioProductoRepository;
import com.coderdot.repository.CompraDetalleRepository;
import com.coderdot.repository.CompraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EntradaMaterialService implements IEntradaMaterialService {

    private final EntradaMaterialRepository _repository;
    private final CompraRepository _compraRepository;
    private final CompraDetalleRepository _compraDetalleRepository;
    private final MessageResult _messageResult;
    private final InventarioProductoRepository _iprepository;
    
    public EntradaMaterialService(EntradaMaterialRepository repository, CompraRepository compraRepository, 
    MessageResult messageResult, CompraDetalleRepository compraDetalleRepository, InventarioProductoRepository iprepository) {
        this._repository = repository;
        this._compraRepository = compraRepository;
        this._messageResult = messageResult;
        this._compraDetalleRepository = compraDetalleRepository;
        this._iprepository = iprepository;
    }

    public List<EntradaMaterial> getAll() {
        return _repository.findAll();
    }

    public Optional<EntradaMaterial> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    @Transactional
    public boolean create(@NonNull EntradaMaterial entity) {

        try {
            Long compra_id = entity.getCompra().getId() != null ? entity.getCompra().getId() : 0;
    
            Compra compra = _compraRepository.findById(compra_id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +compra_id));

            entity.setCompra(compra);

            List<CompraDetalle> detalles = _compraDetalleRepository.findByCompraId(compra_id);

            for (CompraDetalle detalle : detalles) {
                Long producto_id =detalle.getProducto().getId();
                Long inventario_id = compra.getInventario().getId();

                agregarCantidadAlStock(producto_id, inventario_id, detalle.getUnidad());
            }

            _compraRepository.actualizarEntradaATrue(compra_id);

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

        entity.setCompra(producto);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setFecha(entity.getFecha());
                return _repository.save(existingEntity);
            });

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean delete(@NonNull Long id) {
        if (_repository.existsById(id)) {

            EntradaMaterial entrada = _repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " +id));

            List<CompraDetalle> detalles = _compraDetalleRepository.findByCompraId(entrada.getCompra().getId());

            for (CompraDetalle detalle : detalles) {
                Long producto_id = detalle.getProducto().getId();
                Long inventario_id = entrada.getCompra().getInventario().getId();

                reducirCantidadAlStock(producto_id, inventario_id, detalle.getUnidad());
            }

            _compraRepository.actualizarEntradaAFalse(entrada.getCompra().getId());

            _repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    
    public List<EntradaMaterial> obtenerEntradasPorSucursal(Long sucursalId) {
        return _repository.findByCompraInventarioSucursalId(sucursalId);
    }

    private void agregarCantidadAlStock(Long productoId, Long inventarioId, Integer cantidad) {
        InventarioProducto inventarioProducto = _iprepository
                .findByProductoIdAndInventarioId(productoId, inventarioId)
                .orElseThrow(() -> new RuntimeException("InventarioProducto no encontrado con productoId: " + productoId + " e inventarioId: " + inventarioId));

        int nuevoStock = inventarioProducto.getStock() + cantidad;
        inventarioProducto.setStock(nuevoStock);

        _iprepository.save(inventarioProducto);
    }

    private void reducirCantidadAlStock(Long productoId, Long inventarioId, Integer cantidad) {
        InventarioProducto inventarioProducto = _iprepository
                .findByProductoIdAndInventarioId(productoId, inventarioId)
                .orElseThrow(() -> new RuntimeException("InventarioProducto no encontrado con productoId: " + productoId + " e inventarioId: " + inventarioId));

        int nuevoStock = inventarioProducto.getStock() - cantidad;
        inventarioProducto.setStock(nuevoStock);

        _iprepository.save(inventarioProducto);
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}