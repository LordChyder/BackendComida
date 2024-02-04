package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Permiso;
import com.coderdot.entities.PermisoPerfil;
import com.coderdot.entities.Perfil;

public class PermisoPerfilRequest {

    private Long permisoId;
    private Long perfilId;

    public Long getPerfilId() {
        return perfilId;
    }

    public Long getPermisoId() {
        return permisoId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public void setPermisoId(Long permisoId) {
        this.permisoId = permisoId;
    }

    public @NonNull PermisoPerfil toPermisoPerfil() {
        PermisoPerfil entradaMaterial = new PermisoPerfil();
        
        Permiso permiso = new Permiso();
        permiso.setId(this.permisoId);
        
        Perfil perfil = new Perfil();
        perfil.setId(this.perfilId);

        entradaMaterial.setPermiso(permiso);
        entradaMaterial.setPerfil(perfil);

        return entradaMaterial;
    }
}
