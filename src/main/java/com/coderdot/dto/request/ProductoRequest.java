package com.coderdot.dto.request;

import com.coderdot.entities.Categoria;
import com.coderdot.entities.Producto;

public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private String caracteristica;
    private String codigo;
    private Long categoria_id;
    private Boolean estado;

    // Getters y setters

    public Boolean getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto toProducto() {
        Producto producto = new Producto();
        producto.setNombre(this.nombre);
        producto.setDescripcion(this.descripcion);
        producto.setCaracteristica(this.caracteristica);
        producto.setCodigo(this.codigo);
        producto.setEstado(this.estado);
        
        Categoria categoria = new Categoria();
        categoria.setId(this.categoria_id);
        producto.setCategoria(categoria);

        return producto;
    }
}
