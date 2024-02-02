package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.User;
import com.coderdot.entities.PerfilUser;
import com.coderdot.entities.Perfil;

public class PerfilUserRequest {

    private Long userId;
    private Long perfilId;


    public @NonNull PerfilUser toPerfilUser() {
        PerfilUser entradaMaterial = new PerfilUser();
        
        User user = new User();
        user.setId(this.userId);
        
        Perfil perfil = new Perfil();
        perfil.setId(this.perfilId);

        entradaMaterial.setUser(user);
        entradaMaterial.setPerfil(perfil);

        return entradaMaterial;
    }
}
