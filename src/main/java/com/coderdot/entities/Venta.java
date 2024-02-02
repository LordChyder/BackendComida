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
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private String dni;
    private Date fecha;
    private Number total;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "caja_apertura_id", nullable = false)
    private CajaApertura cajaApertura;

    public String getCliente() {
        return cliente;
    }

    public String getDni() {
        return dni;
    }

    public CajaApertura getCajaApertura() {
        return cajaApertura;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getId() {
        return id;
    }

    public Number getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }
    
    public void setCajaApertura(CajaApertura cajaApertura) {
        this.cajaApertura = cajaApertura;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotal(Number total) {
        this.total = total;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
