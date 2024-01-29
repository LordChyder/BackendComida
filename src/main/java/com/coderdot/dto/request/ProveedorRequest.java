package com.coderdot.dto.request;

public class ProveedorRequest {

    private String empresa;
    private String ruc;
    private String representante;
    private String direccion;
    private String telefono;
    private Boolean estado;

    public String getDireccion() {
        return direccion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getRepresentante() {
        return representante;
    }

    public String getRuc() {
        return ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
