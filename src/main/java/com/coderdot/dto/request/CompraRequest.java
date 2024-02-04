package com.coderdot.dto.request;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.coderdot.entities.User;
import com.coderdot.entities.Compra;
import com.coderdot.entities.Proveedor;
import com.coderdot.entities.Inventario;

public class CompraRequest {

    private Date fecha;
    private Number total;
    private Boolean estado;
    private Boolean entrada;
    private Long user_id;
    private Long proveedor_id;
    private Long inventario_id;

    public Long getInventario_id() {
        return inventario_id;
    }

    public void setInventario_id(Long inventario_id) {
        this.inventario_id = inventario_id;
    }

    public Boolean getEntrada() {
        return entrada;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getProveedor_id() {
        return proveedor_id;
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

    public void setProveedor_id(Long proveedor_id) {
        this.proveedor_id = proveedor_id;
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

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public @NonNull Compra toCompra() {
        Compra compra = new Compra();
        compra.setFecha(this.fecha);
        compra.setEntrada(this.entrada);
        compra.setEstado(this.estado);
        compra.setTotal(this.total);
        
        User user = new User();
        user.setId(this.user_id);
        
        Proveedor proveedor = new Proveedor();
        proveedor.setId(this.proveedor_id);
        
        Inventario inventario = new Inventario();
        inventario.setId(this.inventario_id);

        compra.setUser(user);
        compra.setInventario(inventario);
        compra.setProveedor(proveedor);

        return compra;
    }
}
