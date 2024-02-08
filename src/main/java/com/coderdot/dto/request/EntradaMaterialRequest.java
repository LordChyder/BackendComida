package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.EntradaMaterial;
import com.coderdot.entities.Compra;

public class EntradaMaterialRequest {

    private Date fecha;
    private Date fecha_vencimiento;
    private Long compra_id;

    public Long getCompra_id() {
        return compra_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
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
        entradaMaterial.setFecha_vencimiento(this.fecha_vencimiento);
        
        Compra compra = new Compra();
        compra.setId(this.compra_id);

        entradaMaterial.setCompra(compra);

        return entradaMaterial;
    }
}
