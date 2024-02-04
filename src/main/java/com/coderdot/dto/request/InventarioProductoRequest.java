package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Inventario;
import com.coderdot.entities.InventarioProducto;
import com.coderdot.entities.Producto;

public class InventarioProductoRequest {

    private Integer stock;
    private Long inventario_id;
    private Long producto_id;

    public Long getInventario_id() {
        return inventario_id;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public Integer getStock() {
        return stock;
    }
    
    public void setInventario_id(Long inventario_id) {
        this.inventario_id = inventario_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public @NonNull InventarioProducto toInventarioProducto() {
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setStock(this.stock);
        
        Inventario inventario = new Inventario();
        inventario.setId(this.inventario_id);
        
        Producto producto = new Producto();
        producto.setId(this.producto_id);

        inventarioProducto.setInventario(inventario);
        inventarioProducto.setProducto(producto);

        return inventarioProducto;
    }
}
