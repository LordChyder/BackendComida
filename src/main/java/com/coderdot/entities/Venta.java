package com.coderdot.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private String dni;
    private Date fecha;
    private Number total;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "caja_apertura_id", nullable = false)
    private CajaApertura cajaApertura;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumento tipoDocumento;

    public String getCliente() {
        return cliente;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public String getDni() {
        return dni;
    }

    public CajaApertura getCajaApertura() {
        return cajaApertura;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Long getId() {
        return id;
    }

    public Number getTotal() {
        return total;
    }

    public void setCajaApertura(CajaApertura cajaApertura) {
        this.cajaApertura = cajaApertura;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
