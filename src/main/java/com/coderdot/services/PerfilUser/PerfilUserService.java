package com.coderdot.services.PerfilUser;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.PerfilUser;
import com.coderdot.entities.User;
import com.coderdot.repository.PerfilUserRepository;
import com.coderdot.repository.UserRepository;
import com.coderdot.repository.PerfilRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PerfilUserService implements IPerfilUserService {

    private final PerfilUserRepository _repository;
    private final PerfilRepository _perfilRepository;
    private final UserRepository _userRepository;
    
    public PerfilUserService(PerfilUserRepository repository, PerfilRepository perfilRepository, UserRepository userRepository) {
        this._repository = repository;
        this._perfilRepository = perfilRepository;
        this._userRepository = userRepository;
    }

    public List<PerfilUser> getAll() {
        return _repository.findAll();
    }

    public Optional<PerfilUser> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull PerfilUser entity) {

        try {
            Long perfil_id = entity.getPerfil().getId() != null ? entity.getPerfil().getId() : 0;
    
            Perfil perfil = _perfilRepository.findById(perfil_id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrada con id: " +perfil_id));

            Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            User user = _userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

            entity.setPerfil(perfil);
            entity.setUser(user);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  PerfilUser entity) {
        
        Long perfil_id = entity.getPerfil().getId() != null ? entity.getPerfil().getId() : 0;
    
        Perfil perfil = _perfilRepository.findById(perfil_id)
            .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrada con id: " +perfil_id));

        Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;

        User user = _userRepository.findById(user_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

        entity.setPerfil(perfil);
        entity.setUser(user);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setPerfil(entity.getPerfil());
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