package com.example.View;


import javax.swing.*;

import com.example.Controller.FuncionarioController;
import com.example.Model.Funcionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFuncionarioView extends JFrame {
    private JTextField nomeField, cargoField, dataAdmissaoField, salarioField;
    private JButton cadastrarButton;

    public CadastroFuncionarioView() {
        setTitle("Cadastro de Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Criando os campos e botões
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(30, 30, 100, 25);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 30, 200, 25);
        add(nomeField);

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setBounds(30, 70, 100, 25);
        add(cargoLabel);

        cargoField = new JTextField();
        cargoField.setBounds(150, 70, 200, 25);
        add(cargoField);

        JLabel dataAdmissaoLabel = new JLabel("Data de Admissão:");
        dataAdmissaoLabel.setBounds(30, 110, 120, 25);
        add(dataAdmissaoLabel);

        dataAdmissaoField = new JTextField();
        dataAdmissaoField.setBounds(150, 110, 200, 25);
        add(dataAdmissaoField);

        JLabel salarioLabel = new JLabel("Salário:");
        salarioLabel.setBounds(30, 150, 100, 25);
        add(salarioLabel);

        salarioField = new JTextField();
        salarioField.setBounds(150, 150, 200, 25);
        add(salarioField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(150, 200, 100, 30);
        add(cadastrarButton);

        // Ação do botão
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cargo = cargoField.getText();
                String dataAdmissao = dataAdmissaoField.getText();
                double salario = Double.parseDouble(salarioField.getText());

                Funcionario funcionario = new Funcionario(nome, cargo, dataAdmissao, salario);
                FuncionarioController controller = new FuncionarioController();

                if (controller.cadastrarFuncionario(funcionario)) {
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário.");
                }
            }
        });
    }

    public static void main(String[] args) {
        CadastroFuncionarioView view = new CadastroFuncionarioView();
        view.setVisible(true);
    }
}
