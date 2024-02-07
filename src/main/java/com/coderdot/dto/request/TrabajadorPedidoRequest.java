package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Mesa;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.Sucursal;

public class TrabajadorPedidoRequest {

    private Integer n_personas;
    private Boolean estado;
    private Boolean anulado;
    private Long mesa_id;
    private Long sucursal_id;

    public Boolean getAnulado() {
        return anulado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public Long getMesa_id() {
        return mesa_id;
    }

    public Integer getN_personas() {
        return n_personas;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public void setMesa_id(Long mesa_id) {
        this.mesa_id = mesa_id;
    }

    public void setN_personas(Integer n_personas) {
        this.n_personas = n_personas;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public @NonNull Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setAnulado(this.anulado);
        pedido.setEstado(this.estado);
        pedido.setN_personas(this.n_personas);
        pedido.setFecha(new Date());
        
        Mesa mesa = new Mesa();
        mesa.setId(this.mesa_id);
        
        Sucursal sucursal = new Sucursal();
        sucursal.setId(this.sucursal_id);

        pedido.setSucursal(sucursal);
        pedido.setMesa(mesa);

        return pedido;
    }
}
