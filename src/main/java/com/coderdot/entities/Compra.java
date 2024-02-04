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
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private Number total;
    private Boolean estado;
    private Boolean entrada;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;


    public Boolean getEntrada() {
        return entrada;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

   public Inventario getInventario() {
       return inventario;
   }

    public void setEntrada(Boolean entrada) {
        this.entrada = entrada;
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

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
