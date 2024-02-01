package com.coderdot.dto.request;

import org.springframework.lang.NonNull;

import com.coderdot.entities.Establecimiento;
import com.coderdot.entities.Sucursal;

public class SucursalRequest {

    private String representante_legal;
    private String direccion;
    private String administrador;
    private String coordinador;
    private String telefono;
    private Long establecimientoId;


    public String getAdministrador() {
        return administrador;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRepresentante_legal() {
        return representante_legal;
    }

    public String getTelefono() {
        return telefono;
    }

    public Long getEstablecimientoId() {
        return establecimientoId;
    }

    
    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setRepresentante_legal(String representante_legal) {
        this.representante_legal = representante_legal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public @NonNull Sucursal toSucursal() {
        Sucursal sucursal = new Sucursal();
        sucursal.setAdministrador(this.administrador);
        sucursal.setCoordinador(this.coordinador);
        sucursal.setDireccion(this.direccion);
        sucursal.setRepresentante_legal(this.representante_legal);
        sucursal.setTelefono(this.telefono);
        
        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setId(this.establecimientoId);
        sucursal.setEstablecimiento(establecimiento);

        return sucursal;
    }
}
