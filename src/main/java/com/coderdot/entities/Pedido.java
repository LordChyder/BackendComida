package com.coderdot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer n_personas;
    private Boolean estado;
    private Boolean anulado;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

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

    public Integer getN_personas() {
        return n_personas;
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
