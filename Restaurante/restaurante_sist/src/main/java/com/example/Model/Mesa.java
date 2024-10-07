package com.example.Model;

public class Mesa {
    private int id;
    private String status;
    private int numero;

    // Construtor
    public Mesa(int id, String status, int numero) {
        this.id = id;
        this.status = status;
        this.numero = numero;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getNumero() {
        return numero;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
