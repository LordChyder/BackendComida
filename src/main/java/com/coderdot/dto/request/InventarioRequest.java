package com.coderdot.dto.request;

import com.coderdot.entities.Sucursal;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Inventario;

public class InventarioRequest {


    private String nombre;
    private String ubicacion;
    private Long sucursal_id;

    public String getNombre() {
        return nombre;
    }

    public Long getSucursal_id() {
        return sucursal_id;
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

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public @NonNull Inventario toInventario() {
        Inventario inventario = new Inventario();
        inventario.setNombre(this.nombre);
        inventario.setUbicacion(this.ubicacion);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursal_id);

        inventario.setSucursal(sucursal);

        return inventario;
    }
}
