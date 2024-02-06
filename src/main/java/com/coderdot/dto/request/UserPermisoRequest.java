package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Permiso;
import com.coderdot.entities.UserPermiso;
import com.coderdot.entities.User;

public class UserPermisoRequest {

    private Long permisoId;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public Long getPermisoId() {
        return permisoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPermisoId(Long permisoId) {
        this.permisoId = permisoId;
    }

    public @NonNull UserPermiso toUserPermiso() {
        UserPermiso userPermiso = new UserPermiso();
        
        Permiso permiso = new Permiso();
        permiso.setId(this.permisoId);
        
        User perfil = new User();
        perfil.setId(this.userId);

        userPermiso.setPermiso(permiso);
        userPermiso.setUser(perfil);

        return userPermiso;
    }
}
