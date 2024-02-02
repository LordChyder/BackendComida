package com.coderdot.services.VentaDetalle;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Comida;
import com.coderdot.entities.Venta;
import com.coderdot.entities.VentaDetalle;
import com.coderdot.repository.VentaDetalleRepository;
import com.coderdot.repository.ComidaRepository;
import com.coderdot.repository.VentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaDetalleService implements IVentaDetalleService {

    private final VentaDetalleRepository _repository;
    private final ComidaRepository _comidaRepository;
    private final VentaRepository _ventaRepository;
    
    public VentaDetalleService(VentaDetalleRepository repository, VentaRepository ventaRepository
    , ComidaRepository comidaRepository) {
        this._repository = repository;
        this._ventaRepository = ventaRepository;
        this._comidaRepository = comidaRepository;
    }

    public List<VentaDetalle> getAll() {
        return _repository.findAll();
    }

    public Optional<VentaDetalle> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull VentaDetalle entity) {

        try {
            Long comida_id = entity.getComida().getId() != null ? entity.getComida().getId() : 0;
    
            Comida comida = _comidaRepository.findById(comida_id)
                .orElseThrow(() -> new EntityNotFoundException("Comida no encontrada con id: " +comida_id));

                
            Long venta_id = entity.getVenta().getId() != null ? entity.getVenta().getId() : 0;
    
            Venta venta = _ventaRepository.findById(venta_id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " +venta_id));

    
            entity.setComida(comida);
            entity.setVenta(venta);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  VentaDetalle entity) {

        
        Long comida_id = entity.getComida().getId() != null ? entity.getComida().getId() : 0;
    
        Comida comida = _comidaRepository.findById(comida_id)
            .orElseThrow(() -> new EntityNotFoundException("Comida no encontrada con id: " +comida_id));

            
        Long venta_id = entity.getVenta().getId() != null ? entity.getVenta().getId() : 0;

        Venta venta = _ventaRepository.findById(venta_id)
            .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " +venta_id));


        entity.setComida(comida);
        entity.setVenta(venta);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setComida(entity.getComida());
                existingEntity.setPrecio_unitario(entity.getPrecio_unitario());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setUnidad(entity.getUnidad());
                existingEntity.setVenta(entity.getVenta());
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