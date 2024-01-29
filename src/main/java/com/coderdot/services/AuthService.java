package com.coderdot.services;

import com.coderdot.dto.request.SignupRequest;
import com.coderdot.entities.User;

public interface AuthService {
    User createUser(SignupRequest signupRequest);
}
