package com.coderdot.dto.response;

import com.coderdot.entities.PedidoDetalle;

public class PedidoDetalleDTO {
    private PedidoDetalle pedidoDetalle;
    private Float precio;

    public PedidoDetalleDTO(PedidoDetalle pedidoDetalle, Float precio) {
        this.pedidoDetalle = pedidoDetalle;
        this.precio = precio;
    }

    public PedidoDetalle getPedidoDetalle() {
        return pedidoDetalle;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}