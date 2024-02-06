package com.coderdot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "sucursal_comidas",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sucursal_id", "comida_id"})
    }
)
public class SucursalComida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float precio;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    public Long getId() {
        return id;
    }

    public Float getPrecio() {
        return precio;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public Comida getComida() {
        return comida;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
