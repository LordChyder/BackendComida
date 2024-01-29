package com.coderdot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "establecimiento")
public class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;
    private String ruc;
    private String nombre_comercial;
    private String ubicacion;
    private String contacto;
    private String representante_legal;
    private String web_facebook;
    private String web_instagram;

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
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
