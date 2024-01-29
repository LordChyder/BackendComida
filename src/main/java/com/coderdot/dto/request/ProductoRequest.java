package com.coderdot.dto.request;

import com.coderdot.entities.Categoria;
import com.coderdot.entities.Producto;

public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private String caracteristica;
    private String codigo;
    private Long categoriaId;

    // Getters y setters

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

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
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

        Categoria categoria = new Categoria();
        categoria.setId(this.categoriaId);
        producto.setCategoria(categoria);

        return producto;
    }
}
