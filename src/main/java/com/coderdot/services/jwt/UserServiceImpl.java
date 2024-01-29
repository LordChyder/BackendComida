package com.coderdot.services.jwt;

import com.coderdot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository _repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this._repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Write logic to fetch customer from DB

        com.coderdot.entities.User customer = _repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        return new User(customer.getUsername(), customer.getPassword(), Collections.emptyList());
    }
}
