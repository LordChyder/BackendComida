package com.coderdot.dto.request;

public class EstablecimientoRequest {

    private String empresa;
    private String ruc;
    private String nombre_comercial;
    private String ubicacion;
    private String contacto;
    private String representante_legal;
    private String web_facebook;
    private String web_instagram;


    public String getContacto() {
        return contacto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public String getRepresentante_legal() {
        return representante_legal;
    }

    public String getRuc() {
        return ruc;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    public String getWeb_facebook() {
        return web_facebook;
    }

    public String getWeb_instagram() {
        return web_instagram;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }


    public void setRepresentante_legal(String representante_legal) {
        this.representante_legal = representante_legal;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setWeb_facebook(String web_facebook) {
        this.web_facebook = web_facebook;
    }

    public void setWeb_instagram(String web_instagram) {
        this.web_instagram = web_instagram;
    }
}
