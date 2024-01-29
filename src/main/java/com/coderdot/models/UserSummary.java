package com.coderdot.models;

public class UserSummary {

    public Long id;
    public String nombres;
    public String dni;
    public String celular;
    public String username;
    public String email;

    public UserSummary(Long id, String nombres, String dni, String celular, String username, String email) {
        this.id = id;
        this.nombres = nombres;
        this.dni = dni;
        this.celular = celular;
        this.username = username;
        this.email = email;
    }
}
