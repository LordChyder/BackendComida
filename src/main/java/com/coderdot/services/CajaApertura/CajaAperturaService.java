package com.coderdot.services.CajaApertura;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.User;
import com.coderdot.entities.Caja;
import com.coderdot.entities.CajaApertura;
import com.coderdot.repository.CajaAperturaRepository;
import com.coderdot.repository.CajaRepository;
import com.coderdot.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CajaAperturaService implements ICajaAperturaService {

    private final CajaAperturaRepository _repository;
    private final CajaRepository _cajaRepository;
    private final UserRepository _userRepository;

    public CajaAperturaService(CajaAperturaRepository repository, CajaRepository cajaRepository, UserRepository userRepository) {
        this._repository = repository;
        this._cajaRepository = cajaRepository;
        this._userRepository = userRepository;
    }

    public List<CajaApertura> getAll() {
        return _repository.findAll();
    }

    public Optional<CajaApertura> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull CajaApertura entity) {

        try {

            Long caja_id = entity.getCaja().getId() != null ? entity.getCaja().getId() : 0;
    
            Caja caja = _cajaRepository.findById(caja_id)
                .orElseThrow(() -> new EntityNotFoundException("Caja no encontrada con id: " +caja_id));

            Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            User user = _userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Caja no encontrada con id: " +user_id));

            entity.setCaja(caja);
            entity.setUser(user);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  CajaApertura entity) {
        Long caja_id = entity.getCaja().getId() != null ? entity.getCaja().getId() : 0;
    
        Caja caja = _cajaRepository.findById(caja_id)
            .orElseThrow(() -> new EntityNotFoundException("Caja no encontrada con id: " +caja_id));

        Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;

        User user = _userRepository.findById(user_id)
            .orElseThrow(() -> new EntityNotFoundException("Caja no encontrada con id: " +user_id));

        entity.setCaja(caja);
        entity.setUser(user);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setCaja(caja);
                existingEntity.setCerrado(entity.getCerrado());
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setUser(user);
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