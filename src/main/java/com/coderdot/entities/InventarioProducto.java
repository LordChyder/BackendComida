package com.coderdot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventario_producto")
public class InventarioProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Producto getProducto() {
        return producto;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
