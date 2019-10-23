package com.example.sqlite;

public class UsuarioList {
    private String firsname, lastname, email;

    public UsuarioList(String firsname, String lastname, String email) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirsname() {
        return firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}
