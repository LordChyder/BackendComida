package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Inventario;
import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Compra;

public class EntradaMaterialRequest {

    private Date fecha;
    private Long inventarioId;
    private Long compraId;

    public Long getInventarioId() {
        return inventarioId;
    }

    public Long getCompraId() {
        return compraId;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public @NonNull EntradaMaterial toEntradaMaterial() {
        EntradaMaterial entradaMaterial = new EntradaMaterial();
        entradaMaterial.setFecha(this.fecha);
        
        Inventario inventario = new Inventario();
        inventario.setId(this.inventarioId);
        
        Compra compra = new Compra();
        compra.setId(this.compraId);

        entradaMaterial.setInventario(inventario);
        entradaMaterial.setCompra(compra);

        return entradaMaterial;
    }
}
