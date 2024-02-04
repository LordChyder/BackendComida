package com.coderdot.services.SucursalTrabajador;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.entities.User;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.SucursalTrabajadorRepository;
import com.coderdot.repository.SucursalRepository;
import com.coderdot.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SucursalTrabajadorService implements ISucursalTrabajadorService {

    private final SucursalTrabajadorRepository _repository;
    private final UserRepository _userRepository;
    private final SucursalRepository _sucursalRepository;
    private final MessageResult _messageResult;
    
    public SucursalTrabajadorService(SucursalTrabajadorRepository repository, UserRepository userRepository, 
    MessageResult messageResult, SucursalRepository sucursalRepository) {
        this._repository = repository;
        this._userRepository = userRepository;
        this._sucursalRepository = sucursalRepository;
        this._messageResult = messageResult;
    }

    public List<SucursalTrabajador> getAll() {
        return _repository.findAll();
    }

    public Optional<SucursalTrabajador> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull SucursalTrabajador entity) {

        try {
            Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            User user = _userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

            Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;
    
            Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

            entity.setUser(user);
            entity.setSucursal(sucursal);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  SucursalTrabajador entity) {
        Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;

        User user = _userRepository.findById(user_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

        Long sucursal_id = entity.getSucursal().getId() != null ? entity.getSucursal().getId() : 0;

        Sucursal sucursal = _sucursalRepository.findById(sucursal_id)
            .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " +sucursal_id));

        entity.setUser(user);
        entity.setSucursal(sucursal);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setTipo(entity.getTipo());
                existingEntity.setUser(entity.getUser());
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

    public List<SucursalTrabajador> getSucursalTrabajadorPorSucursal(Long sucursalId) {
        return _repository.findBySucursalId(sucursalId);
    }
    
    public MessageResult getResult() {
        return this._messageResult;
    }
}