package com.example.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import com.example.Model.RestauranteConexao;
import com.example.Utils.RelatorioGenerator;

public class GerentePainelView extends JFrame {
    private JButton btnCadastroFuncionario;
    private JButton btnCadastroMesa;
    private JButton btnCadastroPrato;
    private JButton btnCadastroBebida; // Novo botão para cadastro de bebida
    private JButton btnRelatorioFuncionarios; // Botão para relatório de funcionários
    private JButton btnRelatorioPratos; // Botão para relatório de pratos

    public GerentePainelView() {
        setTitle("Painel do Gerente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnCadastroFuncionario = new JButton("Cadastro de Funcionário");
        btnCadastroMesa = new JButton("Cadastro de Mesa");
        btnCadastroPrato = new JButton("Cadastro de Prato");
        btnCadastroBebida = new JButton("Cadastro de Bebida"); // Inicializando o botão de cadastro de bebida
        btnRelatorioFuncionarios = new JButton("Gerar Relatório de Funcionários");
        btnRelatorioPratos = new JButton("Gerar Relatório de Pratos");

        JPanel panel = new JPanel();
        panel.add(btnCadastroFuncionario);
        panel.add(btnCadastroMesa);
        panel.add(btnCadastroPrato);
        panel.add(btnCadastroBebida); // Adicionando o botão de cadastro de bebida ao painel
        panel.add(btnRelatorioFuncionarios);
        panel.add(btnRelatorioPratos);

        add(panel);
        setVisible(true);

        btnCadastroFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de Cadastro de Funcionário
                CadastroFuncionarioView cadastroFuncionarioView = new CadastroFuncionarioView();
                cadastroFuncionarioView.setVisible(true); // Tornar a nova janela visível
            }
        });

        btnCadastroMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMesaView(); // Abre a tela de cadastro de mesa
            }
        });

        btnCadastroPrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de Cadastro de Prato
                CadastroPratoView cadastroPratoView = new CadastroPratoView();
                cadastroPratoView.setVisible(true); // Tornar a nova janela visível
            }
        });

        // Ação para abrir a tela de cadastro de bebida
        btnCadastroBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de Cadastro de Bebida
                CadastroBebidaView cadastroBebidaView = new CadastroBebidaView();
                cadastroBebidaView.setVisible(true); // Tornar a nova janela visível
            }
        });

        // Ação para gerar relatório de funcionários
        btnRelatorioFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = RestauranteConexao.getConnection(); // Conexão com o banco de dados
                if (connection != null) {
                    RelatorioGenerator generator = new RelatorioGenerator(connection);
                    generator.gerarRelatorioFuncionarios("relatorio_funcionarios.txt");
                    JOptionPane.showMessageDialog(panel, "Relatório de Funcionários gerado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Falha ao conectar ao banco de dados.");
                }
            }
        });

        // Ação para gerar relatório de pratos
        btnRelatorioPratos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = RestauranteConexao.getConnection(); // Conexão com o banco de dados
                if (connection != null) {
                    RelatorioGenerator generator = new RelatorioGenerator(connection);
                    generator.gerarRelatorioPratos("relatorio_pratos.txt");
                    JOptionPane.showMessageDialog(panel, "Relatório de Pratos gerado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Falha ao conectar ao banco de dados.");
                }
            }
        });
    }
}
