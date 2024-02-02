package com.coderdot.services.PermisoPerfil;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.Permiso;
import com.coderdot.entities.PermisoPerfil;
import com.coderdot.repository.PermisoPerfilRepository;
import com.coderdot.repository.PermisoRepository;
import com.coderdot.repository.PerfilRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PermisoPerfilService implements IPermisoPerfilService {

    private final PermisoPerfilRepository _repository;
    private final PerfilRepository _perfilRepository;
    private final PermisoRepository _permisoRepository;
    
    public PermisoPerfilService(PermisoPerfilRepository repository, PerfilRepository perfilRepository, PermisoRepository permisoRepository) {
        this._repository = repository;
        this._perfilRepository = perfilRepository;
        this._permisoRepository = permisoRepository;
    }

    public List<PermisoPerfil> getAll() {
        return _repository.findAll();
    }

    public Optional<PermisoPerfil> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull PermisoPerfil entity) {

        try {
            Long perfil_id = entity.getPerfil().getId() != null ? entity.getPerfil().getId() : 0;
    
            Perfil perfil = _perfilRepository.findById(perfil_id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrada con id: " +perfil_id));

            Long permiso_id = entity.getPermiso().getId() != null ? entity.getPermiso().getId() : 0;
    
            Permiso permiso = _permisoRepository.findById(permiso_id)
                .orElseThrow(() -> new EntityNotFoundException("Permiso no encontrada con id: " +permiso_id));

            entity.setPerfil(perfil);
            entity.setPermiso(permiso);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  PermisoPerfil entity) {
        
        Long perfil_id = entity.getPerfil().getId() != null ? entity.getPerfil().getId() : 0;
    
        Perfil perfil = _perfilRepository.findById(perfil_id)
            .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrada con id: " +perfil_id));

        Long permiso_id = entity.getPermiso().getId() != null ? entity.getPermiso().getId() : 0;

        Permiso permiso = _permisoRepository.findById(permiso_id)
            .orElseThrow(() -> new EntityNotFoundException("Permiso no encontrada con id: " +permiso_id));

        entity.setPerfil(perfil);
        entity.setPermiso(permiso);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setPerfil(entity.getPerfil());
                existingEntity.setPermiso(entity.getPermiso());
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