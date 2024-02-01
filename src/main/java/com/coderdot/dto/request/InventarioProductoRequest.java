package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Inventario;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.entities.Producto;

public class InventarioProductoRequest {

    private Integer stock;
    private Long inventarioId;
    private Long productoId;

    public Long getInventarioId() {
        return inventarioId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public Integer getStock() {
        return stock;
    }
    
    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public @NonNull InventarioProducto toInventarioProducto() {
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setStock(this.stock);
        
        Inventario inventario = new Inventario();
        inventario.setId(this.inventarioId);
        
        Producto producto = new Producto();
        producto.setId(this.productoId);

        inventarioProducto.setInventario(inventario);
        inventarioProducto.setProducto(producto);

        return inventarioProducto;
    }
}
