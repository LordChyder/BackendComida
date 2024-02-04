package com.coderdot.services.Mesa;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Sucursal;
import com.coderdot.models.MessageResult;
import com.coderdot.entities.Mesa;
import com.coderdot.repository.MesaRepository;
import com.coderdot.repository.SucursalRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MesaService implements IMesaService {

    private final MesaRepository _repository;
    private final SucursalRepository _sucursalRepository;
    private final MessageResult _messageResult;

    public MesaService(MesaRepository repository, SucursalRepository sucursalRepository, 
    MessageResult messageResult) {
        this._repository = repository;
        this._sucursalRepository = sucursalRepository;
        this._messageResult = messageResult;
    }

    public List<Mesa> getAll() {
        return _repository.findAll();
    }

    public Optional<Mesa> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Mesa entity) {

        try {

            Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;
    
            Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

            entity.setSucursal(sucursal);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Mesa entity) {

        Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;
    
        Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
            .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setNombre(entity.getNombre());
                existingEntity.setSucursal(sucursal);
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

    public List<Mesa> getMesasPorSucursal(Long sucursalId) {
        return _repository.findBySucursalId(sucursalId);
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}