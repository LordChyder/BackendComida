package com.coderdot.entities;


import java.util.Date;

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
    name = "pedidos",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"venta_id", "mesa_id"})
    }
)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer n_personas;
    private Date fecha;
    private Boolean estado;
    private Boolean anulado;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = true)
    private Venta venta;

    public Date getFecha() {
        return fecha;
    }

    public Venta getVenta() {
        return venta;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Integer getN_personas() {
        return n_personas;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setN_personas(Integer n_personas) {
        this.n_personas = n_personas;
    }
}
