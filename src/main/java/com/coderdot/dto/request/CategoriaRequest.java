package com.coderdot.dto.request;

public class CategoriaRequest {
    public String id;
    public String nombre;
    private Boolean estado;

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
