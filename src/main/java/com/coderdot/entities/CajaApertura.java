package com.coderdot.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "caja_apertura",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "fecha"}),
        @UniqueConstraint(columnNames = {"caja_id", "fecha"})
    }
)
public class CajaApertura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private Number total;

    private Boolean cerrado;

    @ManyToOne
    @JoinColumn(name = "caja_id", nullable = false)
    private Caja caja;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

   public Caja getCaja() {
       return caja;
   }

   public Boolean getCerrado() {
       return cerrado;
   }

   public Date getFecha() {
       return fecha;
   }

   public Long getId() {
       return id;
   }

   public Number getTotal() {
       return total;
   }

   public User getUser() {
       return user;
   }

   public void setCaja(Caja caja) {
       this.caja = caja;
   }

   public void setCerrado(Boolean cerrado) {
       this.cerrado = cerrado;
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

   public void setUser(User user) {
       this.user = user;
   }
}
