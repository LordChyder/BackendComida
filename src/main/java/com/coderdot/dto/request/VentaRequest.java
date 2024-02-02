package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.User;
import com.coderdot.entities.Venta;

public class VentaRequest {

    private String cliente;
    private String dni;
    private Date fecha;
    private Number total;
    private Boolean estado;
    private Long userId;
    private Long cajaAperturaId;

    public Long getCajaAperturaId() {
        return cajaAperturaId;
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

    public Long getUserId() {
        return userId;
    }

    public void setCajaAperturaId(Long cajaAperturaId) {
        this.cajaAperturaId = cajaAperturaId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NonNull Venta toVenta() {
        Venta venta = new Venta();
        venta.setCliente(this.cliente);
        venta.setDni(this.dni);
        venta.setFecha(this.fecha);
        venta.setTotal(this.total);
        venta.setEstado(this.estado);
        
        User user = new User();
        user.setId(this.userId);

        venta.setUser(user);
        
        CajaApertura cajaApertura = new CajaApertura();
        cajaApertura.setId(this.cajaAperturaId);

        venta.setCajaApertura(cajaApertura);

        return venta;
    }
}
