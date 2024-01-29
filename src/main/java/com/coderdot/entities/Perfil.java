package com.coderdot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String codigo;

   public void setCodigo(String codigo) {
       this.codigo = codigo;
   }
   
   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public String getCodigo() {
       return codigo;
   }

   public String getNombre() {
       return nombre;
   }
}
