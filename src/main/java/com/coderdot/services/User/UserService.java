package com.coderdot.services.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coderdot.dto.request.SignupRequest;
import com.coderdot.entities.Perfil;
import com.coderdot.entities.PerfilUser;
import com.coderdot.entities.User;
import com.coderdot.models.UserSummary;
import com.coderdot.repository.UserRepository;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<UserSummary> getAllUsers() {
        return userRepository.findAllUserSummaries();
    }

    public Optional<UserSummary> getUserById(Long id) {
        return userRepository.findUserSummaryById(id);
    }

    public boolean createUser( SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return false;
        }

        User user = new User();
        BeanUtils.copyProperties(request, user);

        String hashPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashPassword);
        User createdCustomer = userRepository.save(user);
        user.setId(createdCustomer.getId());
        return true;
    }

    public boolean updateUser(@NonNull Long id, SignupRequest user) {

        try {
            userRepository.findById(id).map(existingUser -> {
                existingUser.setNombres(user.getNombres());
                existingUser.setDni(user.getDni());
                existingUser.setCelular(user.getCelular());
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
    
                return userRepository.save(existingUser);
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(@NonNull Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean userHasPermiso(String username, String perfilCodigo) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.map(user ->
            user.getPerfiles().stream()
                .anyMatch(perfilUser -> perfilUser.getPerfil().getCodigo().equals(perfilCodigo))
        ).orElse(false);
    }

    public List<Perfil> getPerfilesByUserName(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<PerfilUser> perfilUserSet = user.getPerfiles();

            // Aquí puedes convertir PerfilUser a Perfil o trabajar directamente con PerfilUser según tus necesidades
            return perfilUserSet.stream()
                    .map(PerfilUser::getPerfil)
                    .collect(Collectors.toList());
        } else {
            // Manejo de usuario no encontrado (puedes lanzar una excepción, retornar una lista vacía, etc.)
            return Collections.emptyList();
        }
    }
}