package com.coderdot.services.Sucursal;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Establecimiento;
import com.coderdot.entities.Sucursal;
import com.coderdot.repository.EstablecimientoRepository;
import com.coderdot.repository.SucursalRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SucursalService implements ISucursalService {

    private final SucursalRepository _repository;
    private final EstablecimientoRepository _establecimientoRepository;

    public SucursalService(SucursalRepository repository, EstablecimientoRepository establecimientoRepository) {
        this._repository = repository;
        this._establecimientoRepository = establecimientoRepository;
    }

    public List<Sucursal> getAll() {
        return _repository.findAll();
    }

    public Optional<Sucursal> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Sucursal entity) {

        try {

            Long establecimientoId = entity.getEstablecimiento().getId() != null ? entity.getEstablecimiento().getId() : 0;
    
            Establecimiento establecimiento = _establecimientoRepository.findById(establecimientoId)
                .orElseThrow(() -> new EntityNotFoundException("Establecimiento no encontrada con id: " +establecimientoId));

            entity.setEstablecimiento(establecimiento);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Sucursal entity) {

            Long establecimientoId = entity.getEstablecimiento().getId() != null ? entity.getEstablecimiento().getId() : 0;

            Establecimiento establecimiento = _establecimientoRepository.findById(establecimientoId)
                .orElseThrow(() -> new EntityNotFoundException("Establecimiento no encontrada con id: " +establecimientoId));

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setAdministrador(entity.getAdministrador());
                existingEntity.setCoordinador(entity.getCoordinador());
                existingEntity.setDireccion(entity.getDireccion());
                existingEntity.setRepresentante_legal(entity.getRepresentante_legal());
                existingEntity.setTelefono(entity.getTelefono());
                existingEntity.setEstablecimiento(establecimiento);
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