package com.example.carteiramobile;

public class Usuario {

    private int id;
    private String name;

    // Construtores
    public Usuario(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Usuario() {
    }

    // toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
