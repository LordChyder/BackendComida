package com.coderdot.services.PedidoDetalle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.dto.response.PedidoDetalleDTO;
import com.coderdot.entities.Comida;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.PedidoDetalle;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.PedidoDetalleRepository;
import com.coderdot.repository.ComidaRepository;
import com.coderdot.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoDetalleService implements IPedidoDetalleService {

    private final PedidoDetalleRepository _repository;
    private final ComidaRepository _comidaRepository;
    private final MessageResult _messageResult;
    private final PedidoRepository _pedidoRepository;
    
    public PedidoDetalleService(PedidoDetalleRepository repository, PedidoRepository pedidoRepository
    , ComidaRepository comidaRepository, MessageResult messageResult) {
        this._repository = repository;
        this._pedidoRepository = pedidoRepository;
        this._comidaRepository = comidaRepository;
        this._messageResult = messageResult;
    }

    public List<PedidoDetalle> getAll() {
        return _repository.findAll();
    }

    public Optional<PedidoDetalle> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull PedidoDetalle entity) {

        try {
            Long comida_id = entity.getComida().getId() != null ? entity.getComida().getId() : 0;
    
            Comida comida = _comidaRepository.findById(comida_id)
                .orElseThrow(() -> new EntityNotFoundException("Comida no encontrada con id: " +comida_id));

                
            Long pedido_id = entity.getPedido().getId() != null ? entity.getPedido().getId() : 0;
    
            Pedido pedido = _pedidoRepository.findById(pedido_id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrada con id: " +pedido_id));

    
            entity.setComida(comida);
            entity.setPedido(pedido);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  PedidoDetalle entity) {

        
        Long comida_id = entity.getComida().getId() != null ? entity.getComida().getId() : 0;
    
        Comida comida = _comidaRepository.findById(comida_id)
            .orElseThrow(() -> new EntityNotFoundException("Comida no encontrada con id: " +comida_id));

            
        Long pedido_id = entity.getPedido().getId() != null ? entity.getPedido().getId() : 0;

        Pedido pedido = _pedidoRepository.findById(pedido_id)
            .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrada con id: " +pedido_id));


        entity.setComida(comida);
        entity.setPedido(pedido);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setCantidad(entity.getCantidad());
                existingEntity.setComida(entity.getComida());
                existingEntity.setPedido(entity.getPedido());
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
    

    public List<PedidoDetalle> getDetallePorPedido(Long compraId) {
        return _repository.findByPedidoId(compraId);
    }

    public List<PedidoDetalleDTO> findPedidoDetalleWithPrecioByPedidoId(Long pedidoId) {
    List<Object[]> results = _repository.findPedidoDetalleWithPrecioByPedidoId(pedidoId);
    List<PedidoDetalleDTO> dtos = new ArrayList<>();

    for (Object[] result : results) {
        PedidoDetalle pedidoDetalle = (PedidoDetalle) result[0];
        Float precio = (Float) result[1];
        
        PedidoDetalleDTO dto = new PedidoDetalleDTO(pedidoDetalle, precio);
        dtos.add(dto);
    }

    return dtos;
}
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}