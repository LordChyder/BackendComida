package com.coderdot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "perfiles_users")
public class PerfilUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Otros atributos y métodos según sea necesario

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
