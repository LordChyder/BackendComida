package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Comida;
import com.coderdot.entities.Venta;
import com.coderdot.entities.VentaDetalle;

public class VentaDetalleRequest {

    private Float precio_unitario;
    private Integer unidad;
    private Float total;
    private Long comidaId;
    private Long ventaId;


    public @NonNull VentaDetalle toVentaDetalle() {
        VentaDetalle ventaDetalle = new VentaDetalle();
        ventaDetalle.setUnidad(this.unidad);
        ventaDetalle.setTotal(this.total);
        ventaDetalle.setPrecio_unitario(this.precio_unitario);
        
        Venta venta = new Venta();
        venta.setId(this.ventaId);

        ventaDetalle.setVenta(venta);
        
        Comida comida = new Comida();
        comida.setId(this.comidaId);

        ventaDetalle.setComida(comida);

        return ventaDetalle;
    }
}
