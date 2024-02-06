package com.coderdot.services.UserPermiso;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.User;
import com.coderdot.entities.Permiso;
import com.coderdot.entities.UserPermiso;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.UserPermisoRepository;
import com.coderdot.repository.PermisoRepository;
import com.coderdot.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserPermisoService implements IUserPermisoService {

    private final UserPermisoRepository _repository;
    private final UserRepository _userRepository;
    private final PermisoRepository _permisoRepository;
    private final MessageResult _messageResult;
    
    public UserPermisoService(UserPermisoRepository repository, UserRepository userRepository, 
    PermisoRepository permisoRepository, MessageResult messageResult) {
        this._repository = repository;
        this._userRepository = userRepository;
        this._permisoRepository = permisoRepository;
        this._messageResult = messageResult;
    }

    public List<UserPermiso> getAll() {
        return _repository.findAll();
    }

    public Optional<UserPermiso> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull UserPermiso entity) {

        try {
            Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            User user = _userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

            Long permiso_id = entity.getPermiso().getId() != null ? entity.getPermiso().getId() : 0;
    
            Permiso permiso = _permisoRepository.findById(permiso_id)
                .orElseThrow(() -> new EntityNotFoundException("Permiso no encontrada con id: " +permiso_id));

            entity.setUser(user);
            entity.setPermiso(permiso);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  UserPermiso entity) {
        
        Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
        User user = _userRepository.findById(user_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

        Long permiso_id = entity.getPermiso().getId() != null ? entity.getPermiso().getId() : 0;

        Permiso permiso = _permisoRepository.findById(permiso_id)
            .orElseThrow(() -> new EntityNotFoundException("Permiso no encontrada con id: " +permiso_id));

        entity.setUser(user);
        entity.setPermiso(permiso);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setUser(entity.getUser());
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

    public boolean deleteByPermisoAndUser(@NonNull Long permisoId, @NonNull Long userId) {
        _repository.deleteByPermisoIdAndUserId(permisoId, userId);

        return true; // O devuelve el resultado deseado
    }

    public List<Permiso> getPermisosByUserId(Long userId) {
        return _repository.findPermisosByUserId(userId);
    }

    public List<Permiso> getPermisosNotInUser(Long userId) {
        return _repository.findPermisosNotInUser(userId);
    }

    public MessageResult getResult() {
        return this._messageResult;
    }
}