package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Compra;

public class EntradaMaterialRequest {

    private Date fecha;
    private Long compra_id;

    public Long getCompra_id() {
        return compra_id;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setCompra_id(Long compra_id) {
        this.compra_id = compra_id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public @NonNull EntradaMaterial toEntradaMaterial() {
        EntradaMaterial entradaMaterial = new EntradaMaterial();
        entradaMaterial.setFecha(this.fecha);
        
        Compra compra = new Compra();
        compra.setId(this.compra_id);

        entradaMaterial.setCompra(compra);

        return entradaMaterial;
    }
}
