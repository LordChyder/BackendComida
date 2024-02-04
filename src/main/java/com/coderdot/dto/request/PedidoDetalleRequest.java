package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Comida;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.PedidoDetalle;

public class PedidoDetalleRequest {

    private Integer cantidad;
    private Long pedido_id;
    private Long comida_id;

    public Integer getCantidad() {
        return cantidad;
    }

    public Long getComida_id() {
        return comida_id;
    }

    public Long getPedido_id() {
        return pedido_id;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setComida_id(Long comida_id) {
        this.comida_id = comida_id;
    }

    public void setPedido_id(Long pedido_id) {
        this.pedido_id = pedido_id;
    }

    public @NonNull PedidoDetalle toPedidoDetalle() {
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        pedidoDetalle.setCantidad(this.cantidad);
        
        Pedido pedido = new Pedido();
        pedido.setId(this.pedido_id);

        pedidoDetalle.setPedido(pedido);
        
        Comida comida = new Comida();
        comida.setId(this.comida_id);

        pedidoDetalle.setComida(comida);

        return pedidoDetalle;
    }
}
