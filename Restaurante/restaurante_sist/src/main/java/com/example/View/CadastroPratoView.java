package com.example.View;

import javax.swing.*;
import com.example.Controller.PratoController;
import com.example.Model.Prato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPratoView extends JFrame {

    // Componentes da interface
    private JTextField nomePratoField;
    private JTextField descricaoField;
    private JTextField precoField;
    private JButton cadastrarButton;

    public CadastroPratoView() {
        setTitle("Cadastro de Prato");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Criar os campos e botão
        nomePratoField = new JTextField(20);
        descricaoField = new JTextField(20);
        precoField = new JTextField(10);
        cadastrarButton = new JButton("Cadastrar");

        // Configurar layout da interface
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nome do Prato:"));
        panel.add(nomePratoField);
        panel.add(new JLabel("Descrição:"));
        panel.add(descricaoField);
        panel.add(new JLabel("Preço:"));
        panel.add(precoField);
        panel.add(cadastrarButton);

        // Adicionar o painel à janela
        add(panel);

        // Ação ao clicar no botão cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capturar os dados do formulário
                int idGerado = gerarId(); // Método para gerar um ID
                String nomePrato = nomePratoField.getText();
                String descricao = descricaoField.getText();
                double preco = Double.parseDouble(precoField.getText());

                // Criar um objeto Prato com os dados inseridos
                Prato prato = new Prato(idGerado, nomePrato, descricao, preco);

                // Chamar o controller para cadastrar
                PratoController controller = new PratoController();
                if (controller.cadastrarPrato(prato)) {
                    JOptionPane.showMessageDialog(null, "Prato cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato.");
                }

                // Limpar os campos após o cadastro
                nomePratoField.setText("");
                descricaoField.setText("");
                precoField.setText("");
            }
        });
    }

    // Método para iniciar a janela
    public static void main(String[] args) {
        CadastroPratoView view = new CadastroPratoView();
        view.setVisible(true);
    }

    private int gerarId() {
        // Aqui você pode implementar uma lógica para garantir que o ID é único
        // Para fins de exemplo, vamos usar um contador estático
        return ++idCounter;
    }

    // Contador estático para gerar IDs sequencialmente
    private static int idCounter = 0;
}
