package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Comida;
import com.coderdot.entities.Venta;
import com.coderdot.entities.VentaDetalle;

public class VentaDetalleRequest {

    private Float precio_unitario;
    private Integer unidad;
    private Float total;
    private Long comida_id;
    private Long venta_id;

    public Long getComida_id() {
        return comida_id;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public Float getTotal() {
        return total;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public Long getVenta_id() {
        return venta_id;
    }
    

    public void setComida_id(Long comida_id) {
        this.comida_id = comida_id;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public void setVenta_id(Long venta_id) {
        this.venta_id = venta_id;
    }

    public @NonNull VentaDetalle toVentaDetalle() {
        VentaDetalle ventaDetalle = new VentaDetalle();
        ventaDetalle.setUnidad(this.unidad);
        ventaDetalle.setTotal(this.total);
        ventaDetalle.setPrecio_unitario(this.precio_unitario);
        
        Venta venta = new Venta();
        venta.setId(this.venta_id);

        ventaDetalle.setVenta(venta);
        
        Comida comida = new Comida();
        comida.setId(this.comida_id);

        ventaDetalle.setComida(comida);

        return ventaDetalle;
    }
}
