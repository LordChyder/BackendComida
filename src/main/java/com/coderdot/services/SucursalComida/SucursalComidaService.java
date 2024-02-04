package com.coderdot.services.SucursalComida;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalComida;
import com.coderdot.models.MessageResult;
import com.coderdot.entities.Comida;
import com.coderdot.repository.SucursalComidaRepository;
import com.coderdot.repository.SucursalRepository;
import com.coderdot.repository.ComidaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SucursalComidaService implements ISucursalComidaService {

    private final SucursalComidaRepository _repository;
    private final ComidaRepository _comidaRepository;
    private final SucursalRepository _sucursalRepository;
    private final MessageResult _messageResult;
    
    public SucursalComidaService(SucursalComidaRepository repository, MessageResult messageResult, 
    ComidaRepository comidaRepository, SucursalRepository sucursalRepository) {
        this._repository = repository;
        this._comidaRepository = comidaRepository;
        this._sucursalRepository = sucursalRepository;
        this._messageResult = messageResult;
    }

    public List<SucursalComida> getAll() {
        return _repository.findAll();
    }

    public Optional<SucursalComida> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull SucursalComida entity) {

        try {
            Long comida_id = entity.getComida().getId() != null ? entity.getComida().getId() : 0;
    
            Comida comida = _comidaRepository.findById(comida_id)
                .orElseThrow(() -> new EntityNotFoundException("Comida no encontrada con id: " +comida_id));

            Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;
    
            Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

            entity.setComida(comida);
            entity.setSucursal(sucursal);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  SucursalComida entity) {
        Long comida_id = entity.getComida().getId() != null ? entity.getComida().getId() : 0;

        Comida comida = _comidaRepository.findById(comida_id)
            .orElseThrow(() -> new EntityNotFoundException("Comida no encontrada con id: " +comida_id));

        Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;

        Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
            .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

        entity.setComida(comida);
        entity.setSucursal(sucursal);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setComida(entity.getComida());
                existingEntity.setSucursal(entity.getSucursal());
                existingEntity.setPrecio(entity.getPrecio());
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

    public List<SucursalComida> getComidaPorSucursal(Long sucursalId) {
        return _repository.findBySucursalId(sucursalId);
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}