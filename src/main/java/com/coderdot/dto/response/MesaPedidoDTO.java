package com.coderdot.dto.response;

import com.coderdot.entities.Mesa;
import com.coderdot.entities.Pedido;

public class MesaPedidoDTO {
    
    private Mesa mesa;
    private Pedido pedido;

    public MesaPedidoDTO(Mesa mesa, Pedido pedido) {
        this.mesa = mesa;
        this.pedido = pedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
