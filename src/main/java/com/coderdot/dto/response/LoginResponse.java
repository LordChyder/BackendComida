package com.coderdot.dto.response;

import java.util.List;

import com.coderdot.entities.Perfil;

public record LoginResponse(String jwt, List<Perfil> Perfil, String usuario) {
}
