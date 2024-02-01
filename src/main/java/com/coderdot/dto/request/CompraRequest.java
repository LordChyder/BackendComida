package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.User;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Compra;
import com.coderdot.entities.Proveedor;

public class CompraRequest {

    private Date fecha;
    private Number total;
    private Boolean estado;
    private Boolean entrada;
    private Long userId;
    private Long cajaAperturaId;
    private Long proveedorId;

    public Long getCajaAperturaId() {
        return cajaAperturaId;
    }

    public Boolean getEntrada() {
        return entrada;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getProveedorId() {
        return proveedorId;
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

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }
    
    public void setCajaAperturaId(Long cajaAperturaId) {
        this.cajaAperturaId = cajaAperturaId;
    }

    public void setEntrada(Boolean entrada) {
        this.entrada = entrada;
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

    public @NonNull Compra toCompra() {
        Compra compra = new Compra();
        compra.setFecha(this.fecha);
        compra.setEntrada(this.entrada);
        compra.setEstado(this.estado);
        compra.setTotal(this.total);
        
        User user = new User();
        user.setId(this.userId);
        
        CajaApertura cajaAperturaId = new CajaApertura();
        cajaAperturaId.setId(this.cajaAperturaId);
        
        Proveedor proveedor = new Proveedor();
        proveedor.setId(this.proveedorId);

        compra.setUser(user);
        compra.setProveedor(proveedor);
        compra.setCajaApertura(cajaAperturaId);

        return compra;
    }
}
