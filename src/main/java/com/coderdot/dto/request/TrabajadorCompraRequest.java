package com.coderdot.dto.request;

import java.util.Date;

public class TrabajadorCompraRequest {

    private Date fecha;
    private Number total;
    private Boolean estado;
    private Boolean entrada;
    private Long proveedor_id;
    private Long inventario_id;

    public Long getInventario_id() {
        return inventario_id;
    }

    public void setInventario_id(Long inventario_id) {
        this.inventario_id = inventario_id;
    }

    public Boolean getEntrada() {
        return entrada;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getProveedor_id() {
        return proveedor_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Number getTotal() {
        return total;
    }

    public void setProveedor_id(Long proveedor_id) {
        this.proveedor_id = proveedor_id;
    }
    

    public void setEntrada(Boolean entrada) {
        this.entrada = entrada;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTotal(Number total) {
        this.total = total;
    }
   
}
