package com.coderdot.services.Pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Mesa;
import com.coderdot.entities.Pedido;
import com.coderdot.repository.PedidoRepository;
import com.coderdot.repository.MesaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository _repository;
    private final MesaRepository _mesaRepository;
    
    public PedidoService(PedidoRepository repository, MesaRepository mesaRepository) {
        this._repository = repository;
        this._mesaRepository = mesaRepository;
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
    
            entity.setMesa(mesa);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Pedido entity) {

        Long mesa_id = entity.getMesa().getId() != null ? entity.getMesa().getId() : 0;

        Mesa mesa = _mesaRepository.findById(mesa_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +mesa_id));


        entity.setMesa(mesa);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setAnulado(entity.getAnulado());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setMesa(entity.getMesa());
                existingEntity.setN_personas(entity.getN_personas());
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