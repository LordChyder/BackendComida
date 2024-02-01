package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Compra;
import com.coderdot.entities.CompraDetalle;
import com.coderdot.entities.Producto;

public class CompraDetalleRequest {

    private Float precio_unitario;
    private Integer unidad;
    private Float total;
    private Long productoId;
    private Long compraId;

    public Long getCompraDetalleId() {
        return compraId;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public Long getProductoId() {
        return productoId;
    }

    public Float getTotal() {
        return total;
    }

    public Integer getUnidad() {
        return unidad;
    }
    
    public void setCompraDetalleId(Long compraId) {
        this.compraId = compraId;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
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
        compra.setId(this.compraId);
        
        Producto producto = new Producto();
        producto.setId(this.productoId);
        
        compraDetalle.setProducto(producto);
        compraDetalle.setCompra(compra);

        return compraDetalle;
    }
}
