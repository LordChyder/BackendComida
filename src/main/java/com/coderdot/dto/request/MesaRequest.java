package com.coderdot.dto.request;

import com.coderdot.entities.Sucursal;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Mesa;

public class MesaRequest {

    private String nombre;
    private Long sucursal_id;

    public String getNombre() {
        return nombre;
    }

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public @NonNull Mesa toMesa() {
        Mesa mesa = new Mesa();
        mesa.setNombre(this.nombre);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursal_id);

        mesa.setSucursal(sucursal);

        return mesa;
    }
}
