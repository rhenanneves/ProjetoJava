package com.example.view;

import java.sql.Connection;

import javax.swing.*;

import java.awt.*;

import com.example.controller.ClienteController;
import com.example.controller.FuncionarioController;
import com.example.controller.ProdutoController;

public class SupermercadoApp {
    private Connection conn;
    private ClienteController clienteController;
    private FuncionarioController funcionarioController;
    private ProdutoController produtoController;

    public SupermercadoApp(Connection conn) {
        this.conn = conn;
        this.clienteController = new ClienteController(conn);
        this.funcionarioController = new FuncionarioController(conn);
        this.produtoController = new ProdutoController(conn);
        
        // Configuração da interface gráfica
        configurarInterface();
    }

    private void configurarInterface() {
        JFrame frame = new JFrame("Supermercado");
        JTabbedPane tabbedPane = new JTabbedPane();

        // Aba de Cadastro de Clientes
        JPanel panelCliente = new JPanel();
        // Adicione componentes para cadastro de cliente (ex: campos de texto, botões)
        tabbedPane.addTab("Cadastro de Clientes", panelCliente);

        // Aba de Cadastro de Funcionários
        JPanel panelFuncionario = new JPanel();
        // Adicione componentes para cadastro de funcionário
        tabbedPane.addTab("Cadastro de Funcionários", panelFuncionario);

        // Aba de Cadastro de Produtos
        JPanel panelProduto = new JPanel();
        // Adicione componentes para cadastro de produtos
        tabbedPane.addTab("Cadastro de Produtos", panelProduto);

        // Adicione o tabbedPane ao frame
        frame.add(tabbedPane);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Método para inicializar a conexão com o banco de dados
    // ...

    public static void main(String[] args) {
        // Estabeleça a conexão com o banco de dados
        Connection conn = DatabaseConnection.getConnection();
        new SupermercadoApp(conn);
    }
}
