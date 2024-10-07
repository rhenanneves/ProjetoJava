package com.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.Controller.MesaController; // Certifique-se de que você tem este controlador
import com.example.Model.Mesa;

public class CadastroMesaView {
    private JFrame frame;
    private JTextField idField;
    private JTextField numeroField; // Adiciona um campo para o número da mesa
    private JComboBox<String> statusComboBox;
    private MesaController mesaController;

    public CadastroMesaView() {
        mesaController = new MesaController(); // Inicializa o controlador das mesas
        frame = new JFrame("Cadastro de Mesa");
        frame.setSize(300, 250); // Aumenta a altura para acomodar mais campos
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2)); // 4 linhas, 2 colunas

        // Campo para ID da mesa
        frame.add(new JLabel("ID da Mesa:"));
        idField = new JTextField();
        frame.add(idField);

        // Campo para Número da mesa
        frame.add(new JLabel("Número da Mesa:")); // Adiciona um label para o número
        numeroField = new JTextField(); // Campo para o número da mesa
        frame.add(numeroField);

        // ComboBox para Status da mesa
        frame.add(new JLabel("Status da Mesa:"));
        statusComboBox = new JComboBox<>(new String[]{"livre", "reservada", "ocupada"});
        frame.add(statusComboBox);

        // Botão para salvar
        JButton saveButton = new JButton("Salvar Mesa");
        saveButton.addActionListener(new SaveButtonListener());
        frame.add(saveButton);

        // Exibir a janela
        frame.setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Captura os dados inseridos
                int id = Integer.parseInt(idField.getText());
                int numero = Integer.parseInt(numeroField.getText()); // Captura o número da mesa
                String status = (String) statusComboBox.getSelectedItem();

                // Cria a nova mesa e salva no banco de dados
                Mesa novaMesa = new Mesa(id, status, numero); // Modifica para incluir o número
                mesaController.cadastrarMesa(novaMesa); // Método que você deve implementar no controlador

                // Mensagem de sucesso
                JOptionPane.showMessageDialog(frame, "Mesa cadastrada com sucesso!");

                // Limpa os campos
                idField.setText("");
                numeroField.setText(""); // Limpa o campo de número
                statusComboBox.setSelectedIndex(0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um ID e número válidos.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroMesaView());
    }
}
