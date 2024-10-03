package com.example.model;


import java.util.Date;

public class Venda {
    private int id;
    private int idCliente;
    private int idProduto;
    private int quantidade;
    private double valorTotal;
    private Date dataVenda;

    // Construtor, getters e setters
    public Venda(int id, int idCliente, int idProduto, int quantidade, double valorTotal, Date dataVenda) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }
}
