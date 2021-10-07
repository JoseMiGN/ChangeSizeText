package com.example.changesizetext.data.model;

import java.io.Serializable;

/**
 * Clase que guarda la información del login del usuario
 * Esta clase debe implementar la interfaz Serializable porque esta contenida
 * dentro de la clase Mensaje que se pasa dentro de un Intent.
 * java.lang.RuntimeException: Parcelable encountered IOException writing serializable object (name = com.example.changesizetext.data.model.Message)
 */
public class User implements Serializable{
    private String name;
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
