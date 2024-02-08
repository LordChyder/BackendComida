package com.coderdot.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrada_material")
public class EntradaMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private Date fecha_vencimiento;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public Compra getCompra() {
        return compra;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getId() {
        return id;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
