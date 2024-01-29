package com.coderdot.controllers;

import com.coderdot.dto.request.LoginRequest;
import com.coderdot.dto.response.LoginResponse;
import com.coderdot.entities.Perfil;
import com.coderdot.services.User.UserService;
import com.coderdot.services.jwt.UserServiceImpl;
import com.coderdot.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final UserServiceImpl customerService;

    private final UserService service;

    private final JwtUtil jwtUtil;


    public LoginController(AuthenticationManager authenticationManager, UserServiceImpl customerService, UserService vservice, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.service = vservice;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
            return null;
        }

        final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        final List<Perfil> perfil = service.getPerfilesByUserName(userDetails.getUsername());

        return new LoginResponse(jwt, perfil);
    }

}
