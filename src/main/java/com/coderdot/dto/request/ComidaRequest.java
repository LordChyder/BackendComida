package com.coderdot.dto.request;

public class ComidaRequest {

    private String nombre;
    private String codigo;
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
