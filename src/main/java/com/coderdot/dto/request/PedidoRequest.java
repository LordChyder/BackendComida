package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Mesa;
import com.coderdot.entities.Pedido;

public class PedidoRequest {

    private Integer n_personas;
    private Boolean estado;
    private Boolean anulado;
    private Long mesaId;

    public Boolean getAnulado() {
        return anulado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getMesaId() {
        return mesaId;
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
    
    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public void setN_personas(Integer n_personas) {
        this.n_personas = n_personas;
    }

    public @NonNull Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setAnulado(this.anulado);
        pedido.setEstado(this.estado);
        pedido.setN_personas(this.n_personas);
        
        Mesa mesa = new Mesa();
        mesa.setId(this.mesaId);

        pedido.setMesa(mesa);

        return pedido;
    }
}
