package com.coderdot.dto.request;

public class TrabajadorVentaRequest {

    private String cliente;
    private String dni;
    private String tipo_venta;
    private Number total;
    private Boolean estado;
    private Long tipo_documento_id;
    private Long caja_apertura_id;
    private Long tipo_pago_id;
    private Long pedido_id;

    public Long getCaja_apertura_id() {
        return caja_apertura_id;
    }

    public String getTipo_venta() {
		return tipo_venta;
	}

    public Long getTipo_documento_id() {
        return tipo_documento_id;
    }

    public Long getTipo_pago_id() {
        return tipo_pago_id;
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

    public Number getTotal() {
        return total;
    }
    
    public void setTipo_venta(String tipo_venta) {
		this.tipo_venta = tipo_venta;
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

    public void setTotal(Number total) {
        this.total = total;
    }

    public void setTipo_documento_id(Long tipo_documento_id) {
        this.tipo_documento_id = tipo_documento_id;
    }

    public void setTipo_pago_id(Long tipo_pago_id) {
        this.tipo_pago_id = tipo_pago_id;
    }
}
