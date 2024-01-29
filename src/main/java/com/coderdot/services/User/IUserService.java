package com.coderdot.services.User;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.dto.request.SignupRequest;
import com.coderdot.entities.Perfil;
import com.coderdot.models.UserSummary;

@Service
public interface IUserService {

    public List<UserSummary> getAllUsers();

    public Optional<UserSummary> getUserById(Long id);

    public boolean createUser( SignupRequest request);

    public boolean updateUser(@NonNull  Long id, SignupRequest user);

    public boolean deleteUser(@NonNull  Long id);

    public boolean userHasPermiso(String username, String perfilCodigo);

    public List<Perfil> getPerfilesByUserName(String username);
}