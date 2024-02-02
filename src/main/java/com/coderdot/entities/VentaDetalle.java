package com.coderdot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta_detalles")
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float precio_unitario;
    private Integer unidad;
    private Float total;

    @ManyToOne
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

   
    public Comida getComida() {
        return comida;
    }


    public Long getId() {
        return id;
    }
    public Float getPrecio_unitario() {
        return precio_unitario;
    }
    public Float getTotal() {
        return total;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
