package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Compra;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.Producto;

public class CompraDetalleRequest {

    private Float precio_unitario;
    private Integer unidad;
    private Float total;
    private Long producto_id;
    private Long compra_id;

    public Long getCompra_id() {
        return compra_id;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public Float getTotal() {
        return total;
    }

    public Integer getUnidad() {
        return unidad;
    }
    
    public void setCompra_id(Long compra_id) {
        this.compra_id = compra_id;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public @NonNull CompraDetalle toCompraDetalle() {
        CompraDetalle compraDetalle = new CompraDetalle();
        compraDetalle.setPrecio_unitario(this.precio_unitario);
        compraDetalle.setUnidad(this.unidad);
        compraDetalle.setTotal(this.total);
        
        Compra compra = new Compra();
        compra.setId(this.compra_id);
        
        Producto producto = new Producto();
        producto.setId(this.producto_id);
        
        compraDetalle.setProducto(producto);
        compraDetalle.setCompra(compra);

        return compraDetalle;
    }
}
