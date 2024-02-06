package com.coderdot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    private Permiso permiso;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Otros atributos y métodos según sea necesario

    public Long getId() {
        return id;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
