package com.example.model;


public class Cliente extends Pessoa {
    private String telefone;
    private boolean vip;

    public Cliente(int id, String nome, String cpf, String telefone, boolean vip) {
        super(id, nome, cpf);
        this.telefone = telefone;
        this.vip = vip;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isVip() {
        return vip;
    }
}
