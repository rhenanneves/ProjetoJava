package com.example.model;



public class Produto extends ProdutoBase {
    private int quantidade;

    public Produto(int id, String nome, String codigo, double preco, int quantidade) {
        super(id, nome, codigo, preco);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
