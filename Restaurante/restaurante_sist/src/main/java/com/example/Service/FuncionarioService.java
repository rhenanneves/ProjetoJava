package com.example.Service;

import com.example.Controller.FuncionarioController;
import com.example.Model.Funcionario;

public class FuncionarioService {

    private FuncionarioController funcionarioController;

    // Construtor que recebe o controller
    public FuncionarioService(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
    }

    // Método para cadastrar um funcionário
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        // Verifica se o nome do funcionário está vazio
        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            System.out.println("Erro: O nome do funcionário não pode estar vazio.");
            return false;
        }

        // Verifica se o salário é negativo
        if (funcionario.getSalario() < 0) {
            System.out.println("Erro: O salário do funcionário não pode ser negativo.");
            return false;
        }

        // Chama o controller para realizar o cadastro
        return funcionarioController.cadastrarFuncionario(funcionario);
    }
}
