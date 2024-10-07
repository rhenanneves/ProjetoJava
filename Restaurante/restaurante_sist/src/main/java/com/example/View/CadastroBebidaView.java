package com.example.View;

import javax.swing.*;
import com.example.Controller.BebidaController;
import com.example.Model.Bebida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroBebidaView extends JFrame {

    // Componentes da interface
    private JTextField nomeBebidaField;
    private JTextField descricaoField;
    private JTextField precoField;
    private JButton cadastrarButton;

    public CadastroBebidaView() {
        setTitle("Cadastro de Bebida");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Criar os campos e botão
        nomeBebidaField = new JTextField(20);
        descricaoField = new JTextField(20);
        precoField = new JTextField(10);
        cadastrarButton = new JButton("Cadastrar");

        // Configurar layout da interface
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nome da Bebida:"));
        panel.add(nomeBebidaField);
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
                String nomeBebida = nomeBebidaField.getText();
                String descricao = descricaoField.getText();
                double preco = Double.parseDouble(precoField.getText());

                // Criar um objeto Bebida com os dados inseridos
                Bebida bebida = new Bebida(idGerado, nomeBebida, descricao, preco);

                // Chamar o controller para cadastrar
                BebidaController controller = new BebidaController();
                if (controller.cadastrarBebida(bebida)) {
                    JOptionPane.showMessageDialog(null, "Bebida cadastrada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar bebida.");
                }

                // Limpar os campos após o cadastro
                nomeBebidaField.setText("");
                descricaoField.setText("");
                precoField.setText("");
            }
        });
    }

    // Método para iniciar a janela
    public static void main(String[] args) {
        CadastroBebidaView view = new CadastroBebidaView();
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
