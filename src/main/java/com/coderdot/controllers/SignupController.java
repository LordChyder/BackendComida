package com.coderdot.controllers;

import com.coderdot.dto.request.LoginRequest;
import com.coderdot.dto.request.SignupRequest;
import com.coderdot.dto.response.LoginSignUpResponse;
import com.coderdot.entities.User;
import com.coderdot.services.AuthService;
import com.coderdot.services.jwt.UserServiceImpl;
import com.coderdot.utils.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    private final UserServiceImpl customerService;

    public SignupController(UserServiceImpl customerService, AuthService authService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        User createdUser = authService.createUser(signupRequest);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create User");
        }
    }

    @PostMapping("/index")
    public ResponseEntity<?> signupUserIndex(@RequestBody SignupRequest signupRequest) {
        User createdUser = authService.createUser(signupRequest);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.login(new LoginRequest(signupRequest.getUsername(), signupRequest.getPassword())));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create User");
        }
    }

    private LoginSignUpResponse login(LoginRequest loginRequest) {
        

        final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new LoginSignUpResponse(jwt);
    }
}
