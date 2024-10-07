package com.example.Model;

public class Gerente extends Funcionario {
    public Gerente(String nome, String cargo, String dataAdmissao, double salario) {
        super(nome, cargo, dataAdmissao, salario); // Sem o 'id'
    }
    
}
