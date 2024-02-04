package com.coderdot.services;

import com.coderdot.dto.request.SignupRequest;
import com.coderdot.entities.User;
import com.coderdot.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository _repository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this._repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createUser(SignupRequest signupRequest) {
        System.out.println("------------------------");
        System.out.println(signupRequest.getUsername());
        System.out.println(_repository.existsByUsername(signupRequest.getUsername()));
        
        if (_repository.existsByUsername(signupRequest.getUsername())) {
            return null;
        }


        User user = new User();
        BeanUtils.copyProperties(signupRequest,user);

        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);
        User createdCustomer = _repository.save(user);
        user.setId(createdCustomer.getId());
        return user;
    }
}
