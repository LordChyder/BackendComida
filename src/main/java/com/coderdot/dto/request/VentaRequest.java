package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Venta;

public class VentaRequest {

    private String cliente;
    private String dni;
    private Date fecha;
    private Number total;
    private Boolean estado;
    private Long caja_apertura_id;
    private Long pedido_id;

    public Long getCaja_apertura_id() {
        return caja_apertura_id;
    }

    public Long getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Long pedido_id) {
        this.pedido_id = pedido_id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDni() {
        return dni;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Number getTotal() {
        return total;
    }

    public void setCaja_apertura_id(Long caja_apertura_id) {
        this.caja_apertura_id = caja_apertura_id;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public @NonNull Venta toVenta() {
        Venta venta = new Venta();
        venta.setCliente(this.cliente);
        venta.setDni(this.dni);
        venta.setFecha(this.fecha);
        venta.setTotal(this.total);
        venta.setEstado(this.estado);

        CajaApertura cajaApertura = new CajaApertura();
        cajaApertura.setId(this.caja_apertura_id);

        venta.setCajaApertura(cajaApertura);

        return venta;
    }
}
