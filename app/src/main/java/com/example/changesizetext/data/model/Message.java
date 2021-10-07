package com.example.changesizetext.data.model;

import java.io.Serializable;

/**
 * Clase que guarda un mensaje de un usuario que ha iniciado sesión
 */
public class Message implements Serializable {
    private User user;
    private String message;
    private int size;

    public Message() {
    }

    public Message(User user, String message, int size) {
        this.user = user;
        this.message = message;
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", size=" + size +
                '}';
    }
}
