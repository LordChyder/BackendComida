package com.coderdot.services.Pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderdot.entities.Mesa;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.Sucursal;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.PedidoRepository;
import com.coderdot.repository.SucursalRepository;
import com.coderdot.repository.MesaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository _repository;
    private final MesaRepository _mesaRepository;
    private final SucursalRepository _sucursalRepository;
    private final MessageResult _messageResult;
    
    public PedidoService(PedidoRepository repository, MesaRepository mesaRepository, 
    MessageResult messageResult, SucursalRepository sucursalRepository) {
        this._repository = repository;
        this._mesaRepository = mesaRepository;
        this._messageResult = messageResult;
        this._sucursalRepository = sucursalRepository;
    }

    public List<Pedido> getAll() {
        return _repository.findAll();
    }

    public Optional<Pedido> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Pedido entity) {

        try {
            Long mesa_id = entity.getMesa().getId() != null ? entity.getMesa().getId() : 0;
    
            Mesa mesa = _mesaRepository.findById(mesa_id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " +mesa_id));

            Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;
    
            Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));
    
            entity.setMesa(mesa);
            entity.setSucursal(sucursal);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Pedido entity) {

        Long mesa_id = entity.getMesa().getId() != null ? entity.getMesa().getId() : 0;

        Mesa mesa = _mesaRepository.findById(mesa_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +mesa_id));

        Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;

        Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
            .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " +sucursal_id));

        entity.setMesa(mesa);
        entity.setSucursal(sucursal);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setAnulado(entity.getAnulado());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setMesa(entity.getMesa());
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setN_personas(entity.getN_personas());
                existingEntity.setSucursal(entity.getSucursal());
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
    

    @Transactional
    public boolean setClose(Long pedidoId) {
        _repository.actualizarEstado(pedidoId);
        
        return true;
    }
    
    public List<Pedido> getPedidosActivos(Long sucursalId) {
        return _repository.findBySucursalIdAndEstadoTrueAndAnuladoFalseAndVentaIsNull(sucursalId);
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}