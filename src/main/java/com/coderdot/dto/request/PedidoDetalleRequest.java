package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Comida;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.PedidoDetalle;

public class PedidoDetalleRequest {

    private Integer cantidad;
    private Float precio_unitario;
    private Long pedidoId;
    private Long comidaId;

    public Integer getCantidad() {
        return cantidad;
    }

    public Long getComidaId() {
        return comidaId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setComidaId(Long comidaId) {
        this.comidaId = comidaId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public @NonNull PedidoDetalle toPedidoDetalle() {
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        pedidoDetalle.setCantidad(this.cantidad);
        pedidoDetalle.setPrecio_unitario(this.precio_unitario);
        
        Pedido pedido = new Pedido();
        pedido.setId(this.pedidoId);

        pedidoDetalle.setPedido(pedido);
        
        Comida comida = new Comida();
        comida.setId(this.comidaId);

        pedidoDetalle.setComida(comida);

        return pedidoDetalle;
    }
}
