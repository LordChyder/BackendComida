package com.coderdot.dto.response;

import java.util.List;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.Permiso;

public record LoginResponse(String jwt, List<Perfil> Perfil, String usuario, List<Permiso> permiso, boolean trabajador) {
}
