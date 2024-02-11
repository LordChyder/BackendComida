package com.coderdot.dto.request;

public class CategoriaSavedRequest {
    public String nombre;

    public Boolean estado;

    public Boolean getEstado() {
        return estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
