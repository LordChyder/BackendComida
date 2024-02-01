package com.coderdot.dto.request;

import com.coderdot.entities.Sucursal;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Inventario;

public class InventarioRequest {


    private String nombre;
    private String ubicacion;
    private Long sucursalId;

    public String getNombre() {
        return nombre;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public @NonNull Inventario toInventario() {
        Inventario inventario = new Inventario();
        inventario.setNombre(this.nombre);
        inventario.setUbicacion(this.ubicacion);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursalId);

        inventario.setSucursal(sucursal);

        return inventario;
    }
}
