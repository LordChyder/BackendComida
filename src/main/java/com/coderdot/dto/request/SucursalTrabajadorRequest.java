package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.entities.User;

public class SucursalTrabajadorRequest {

    private String tipo;
    private Long sucursalId;
    private Long userId;

    public Long getSucursalId() {
        return sucursalId;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NonNull SucursalTrabajador toSucursalTrabajador() {
        SucursalTrabajador sucursalTrabajador = new SucursalTrabajador();
        sucursalTrabajador.setTipo(this.tipo);
        
        User user = new User();
        user.setId(this.userId);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursalId);

        sucursalTrabajador.setUser(user);
        sucursalTrabajador.setSucursal(sucursal);

        return sucursalTrabajador;
    }
}
