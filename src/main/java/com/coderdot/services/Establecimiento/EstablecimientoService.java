package com.coderdot.services.Establecimiento;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Establecimiento;
import com.coderdot.repository.EstablecimientoRepository;

@Service
public class EstablecimientoService implements IEstablecimientoService {

    private final EstablecimientoRepository _repository;

    public EstablecimientoService(EstablecimientoRepository repository) {
        this._repository = repository;
    }

    public List<Establecimiento> getAll() {
        return _repository.findAll();
    }

    public Optional<Establecimiento> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Establecimiento entity) {
        try {
            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Establecimiento entity) {

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setContacto(entity.getContacto());
                existingEntity.setEmpresa(entity.getEmpresa());
                existingEntity.setNombre_comercial(entity.getNombre_comercial());
                existingEntity.setRepresentante_legal(entity.getRepresentante_legal());
                existingEntity.setRuc(entity.getRuc());
                existingEntity.setUbicacion(entity.getUbicacion());
                existingEntity.setWeb_facebook(entity.getWeb_facebook());
                existingEntity.setWeb_instagram(entity.getWeb_instagram());
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