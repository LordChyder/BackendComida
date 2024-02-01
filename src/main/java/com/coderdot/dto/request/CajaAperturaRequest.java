package com.coderdot.dto.request;

import com.coderdot.entities.User;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Caja;
import com.coderdot.entities.CajaApertura;

public class CajaAperturaRequest {


    private Date fecha;
    private Number total;
    private Boolean cerrado;
    private Long userId;
    private Long cajaId;

    public Long getCajaId() {
        return cajaId;
    }

    public Boolean getCerrado() {
        return cerrado;
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

    public void setCajaId(Long cajaId) {
        this.cajaId = cajaId;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
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

    public @NonNull CajaApertura toCajaApertura() {
        CajaApertura cajaApertura = new CajaApertura();
        cajaApertura.setCerrado(this.cerrado);
        cajaApertura.setFecha(this.fecha);
        cajaApertura.setTotal(this.total);
        
        Caja caja = new Caja();
        caja.setId(this.cajaId);
        
        User user = new User();
        user.setId(this.userId);

        cajaApertura.setUser(user);
        cajaApertura.setCaja(caja);

        return cajaApertura;
    }
}
