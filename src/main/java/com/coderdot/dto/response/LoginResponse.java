package com.coderdot.dto.response;

import java.util.List;

import com.coderdot.entities.Perfil;
import com.coderdot.entities.Permiso;
import com.coderdot.entities.SucursalTrabajador;

public record LoginResponse(String jwt, List<Perfil> Perfil, 
String usuario, List<Permiso> permiso, boolean trabajador, List<SucursalTrabajador> sucursales) {
}
