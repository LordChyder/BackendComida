package com.coderdot.dto.request;

public class TipoPagoRequest {
    public String nombre;

    public Boolean estado;

    public String getNombre() {
        return nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
