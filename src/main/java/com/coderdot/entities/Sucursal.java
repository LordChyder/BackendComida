package com.coderdot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "surcursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String representante_legal;
    private String direccion;
    private String administrador;
    private String coordinador;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "establecimiento_id", nullable = false)
    private Establecimiento establecimiento;

    public Long getId() {
        return id;
    }

    public String getAdministrador() {
        return administrador;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRepresentante_legal() {
        return representante_legal;
    }

    public String getTelefono() {
        return telefono;
    }


    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRepresentante_legal(String representante_legal) {
        this.representante_legal = representante_legal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
