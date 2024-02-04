package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalTrabajador;
import com.coderdot.entities.User;

public class SucursalTrabajadorRequest {

    private String tipo;
    private Long sucursal_id;
    private Long user_id;

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public @NonNull SucursalTrabajador toSucursalTrabajador() {
        SucursalTrabajador sucursalTrabajador = new SucursalTrabajador();
        sucursalTrabajador.setTipo(this.tipo);
        
        User user = new User();
        user.setId(this.user_id);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursal_id);

        sucursalTrabajador.setUser(user);
        sucursalTrabajador.setSucursal(sucursal);

        return sucursalTrabajador;
    }
}
