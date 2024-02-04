package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalComida;
import com.coderdot.entities.Comida;

public class SucursalComidaRequest {

    private Long sucursal_id;
    private Long comida_id;
    private Float precio;

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public Long getComida_id() {
        return comida_id;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public void setComida_id(Long comida_id) {
        this.comida_id = comida_id;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public @NonNull SucursalComida toSucursalComida() {
        SucursalComida sucursalTrabajador = new SucursalComida();
        sucursalTrabajador.setPrecio(this.precio);
        
        Comida comida = new Comida();
        comida.setId(this.comida_id);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursal_id);

        sucursalTrabajador.setComida(comida);
        sucursalTrabajador.setSucursal(sucursal);

        return sucursalTrabajador;
    }
}
