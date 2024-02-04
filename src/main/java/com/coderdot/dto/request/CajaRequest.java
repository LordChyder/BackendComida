package com.coderdot.dto.request;

import com.coderdot.entities.Sucursal;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Caja;

public class CajaRequest {

    private String nombre;
    private String codigo;
    private Long sucursal_id;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public @NonNull Caja toCaja() {
        Caja caja = new Caja();
        caja.setCodigo(this.codigo);
        caja.setNombre(this.nombre);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursal_id);

        caja.setSucursal(sucursal);

        return caja;
    }
}
