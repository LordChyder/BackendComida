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
    private Long user_id;
    private Long caja_id;

    public Long getCaja_id() {
        return caja_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setCaja_id(Long caja_id) {
        this.caja_id = caja_id;
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
    
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public @NonNull CajaApertura toCajaApertura() {
        CajaApertura cajaApertura = new CajaApertura();
        cajaApertura.setCerrado(this.cerrado);
        System.out.println(this.fecha);
        cajaApertura.setFecha(this.fecha);
        cajaApertura.setTotal(this.total);
        
        Caja caja = new Caja();
        caja.setId(this.caja_id);
        
        User user = new User();
        user.setId(this.user_id);

        cajaApertura.setUser(user);
        cajaApertura.setCaja(caja);

        return cajaApertura;
    }
}
