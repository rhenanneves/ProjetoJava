package com.example.model;

public abstract class ProdutoBase {
    private int id;
    private String nome;
    private String codigo;
    private double preco;

    public ProdutoBase(int id, String nome, String codigo, double preco) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }
}
