package com.coderdot.dto.request;

import com.coderdot.entities.Sucursal;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Caja;

public class CajaRequest {

    private String nombre;
    private String codigo;
    private Long sucursalId;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public @NonNull Caja toCaja() {
        Caja caja = new Caja();
        caja.setCodigo(this.codigo);
        caja.setNombre(this.nombre);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursalId);

        caja.setSucursal(sucursal);

        return caja;
    }
}
