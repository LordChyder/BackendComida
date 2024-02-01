package com.coderdot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra_detalles")
public class CompraDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float precio_unitario;
    private Integer unidad;
    private Float total;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    public Compra getCompra() {
        return compra;
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

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
