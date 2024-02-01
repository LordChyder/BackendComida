package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Sucursal;
import com.coderdot.entities.SucursalComida;
import com.coderdot.entities.Comida;

public class SucursalComidaRequest {

    private Long sucursalId;
    private Long comidaId;

    public Long getSucursalId() {
        return sucursalId;
    }

    public Long getComidaId() {
        return comidaId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public void setComidaId(Long comidaId) {
        this.comidaId = comidaId;
    }

    public @NonNull SucursalComida toSucursalComida() {
        SucursalComida sucursalTrabajador = new SucursalComida();
        
        Comida comida = new Comida();
        comida.setId(this.comidaId);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursalId);

        sucursalTrabajador.setComida(comida);
        sucursalTrabajador.setSucursal(sucursal);

        return sucursalTrabajador;
    }
}
