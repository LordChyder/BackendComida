package com.coderdot.services.Venta;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.User;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Venta;
import com.coderdot.repository.VentaRepository;
import com.coderdot.repository.CajaAperturaRepository;
import com.coderdot.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository _repository;
    private final UserRepository _userRepository;
    private final CajaAperturaRepository _cajaAperturaRepository;
    
    public VentaService(VentaRepository repository, UserRepository userRepository
    , CajaAperturaRepository cajaAperturaRepository) {
        this._repository = repository;
        this._userRepository = userRepository;
        this._cajaAperturaRepository = cajaAperturaRepository;
    }

    public List<Venta> getAll() {
        return _repository.findAll();
    }

    public Optional<Venta> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Venta entity) {

        try {
            Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            User user = _userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));
    
            Long cajaApertura_id = entity.getCajaApertura().getId() != null ? entity.getCajaApertura().getId() : 0;
    
            CajaApertura cajaApertura = _cajaAperturaRepository.findById(cajaApertura_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +cajaApertura_id));
    
            entity.setUser(user);
            entity.setCajaApertura(cajaApertura);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Venta entity) {
        Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;

        User user = _userRepository.findById(user_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

        Long cajaApertura_id = entity.getCajaApertura().getId() != null ? entity.getCajaApertura().getId() : 0;

        CajaApertura cajaApertura = _cajaAperturaRepository.findById(cajaApertura_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +cajaApertura_id));

        entity.setUser(user);
        entity.setCajaApertura(cajaApertura);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setCajaApertura(entity.getCajaApertura());
                existingEntity.setCliente(entity.getCliente());
                existingEntity.setDni(entity.getDni());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setUser(entity.getUser());
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