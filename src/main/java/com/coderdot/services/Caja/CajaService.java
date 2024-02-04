package com.coderdot.services.Caja;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Sucursal;
import com.coderdot.models.MessageResult;
import com.coderdot.entities.Caja;
import com.coderdot.repository.CajaRepository;
import com.coderdot.repository.SucursalRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CajaService implements ICajaService {

    private final CajaRepository _repository;
    private final SucursalRepository _sucursalRepository;
    private final MessageResult _messageResult;

    public CajaService(CajaRepository repository, SucursalRepository sucursalRepository, 
    MessageResult messageResult) {
        this._repository = repository;
        this._sucursalRepository = sucursalRepository;
        this._messageResult = messageResult;
    }

    public List<Caja> getAll() {
        return _repository.findAll();
    }

    public Optional<Caja> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Caja entity) {

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

    public boolean update(@NonNull Long id,  Caja entity) {

        Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;
    
        Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
            .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setCodigo(entity.getCodigo());
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
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}