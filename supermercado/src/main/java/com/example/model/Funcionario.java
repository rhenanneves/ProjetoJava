package com.example.model;


public class Funcionario extends Pessoa {
    private String funcao;

    public Funcionario(int id, String nome, String cpf, String funcao) {
        super(id, nome, cpf);
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }
}
