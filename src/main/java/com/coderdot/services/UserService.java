package com.coderdot.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.PerfilUser;
import com.coderdot.entities.User;
import com.coderdot.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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