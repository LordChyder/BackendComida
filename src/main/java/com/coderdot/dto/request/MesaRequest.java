package com.coderdot.dto.request;

import com.coderdot.entities.Sucursal;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Mesa;

public class MesaRequest {

    private String nombre;
    private Long sucursalId;

    public String getNombre() {
        return nombre;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public @NonNull Mesa toMesa() {
        Mesa mesa = new Mesa();
        mesa.setNombre(this.nombre);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursalId);

        mesa.setSucursal(sucursal);

        return mesa;
    }
}
