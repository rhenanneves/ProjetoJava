package com.example.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import com.example.Controller.PratoController; // Controlador para gerenciar os pratos
import com.example.Controller.BebidaController; // Controlador para gerenciar as bebidas
import com.example.Model.Prato;
import com.example.Model.Bebida; // Certifique-se de que a classe Bebida esteja presente

public class SelecionarPratoView {
    private JFrame frame;
    private JList<String> pratosList;
    private DefaultListModel<String> pratosListModel;
    private JList<String> bebidasList;
    private DefaultListModel<String> bebidasListModel;
    private MesaDetalheView mesaDetalheView; // Referência para a view da mesa

    public SelecionarPratoView(MesaDetalheView mesaDetalheView) {
        this.mesaDetalheView = mesaDetalheView; // Armazena a referência da mesa
        frame = new JFrame("Selecione Pratos e Bebidas");
        frame.setSize(600, 600); // Aumentando a altura para acomodar as duas listas
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Cria um painel para adicionar margens
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona espaço interno
        frame.add(panel, new GridBagConstraints()); // Adiciona o painel

        // Modelo da lista para exibir os pratos
        pratosListModel = new DefaultListModel<>();
        pratosList = new JList<>(pratosListModel);
        pratosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite apenas uma seleção
        pratosList.setFont(new Font("Arial", Font.PLAIN, 16)); // Altera a fonte
        JScrollPane pratosScrollPane = new JScrollPane(pratosList);

        // Criação do layout para os pratos
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JLabel("Pratos"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(pratosScrollPane, gbc);

        // Modelo da lista para exibir as bebidas
        bebidasListModel = new DefaultListModel<>();
        bebidasList = new JList<>(bebidasListModel);
        bebidasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite apenas uma seleção
        bebidasList.setFont(new Font("Arial", Font.PLAIN, 16)); // Altera a fonte
        JScrollPane bebidasScrollPane = new JScrollPane(bebidasList);

        // Criação do layout para as bebidas
        gbc.gridx = 0;
        gbc.gridy = 2; // Mova para a próxima linha
        panel.add(new JLabel("Bebidas"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(bebidasScrollPane, gbc);

        // Botão para confirmar a seleção
        JButton selecionarButton = new JButton("Selecionar");
        selecionarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Altera a fonte do botão
        selecionarButton.setBackground(new Color(76, 175, 80)); // Cor de fundo do botão
        selecionarButton.setForeground(Color.WHITE); // Cor do texto do botão
        selecionarButton.addActionListener(new SelecionarButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 4; // Ajuste a posição do botão
        panel.add(selecionarButton, gbc);

        // Carregar pratos e bebidas disponíveis
        carregarPratos();
        carregarBebidas();

        // Exibir a janela
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centraliza a janela
    }

    private void carregarPratos() {
        PratoController pratoController = new PratoController(); // Controlador para pratos
        List<Prato> pratos = pratoController.listarPratos(); // Supondo que você tenha esse método

        // Adiciona pratos na lista
        for (Prato prato : pratos) {
            pratosListModel.addElement(prato.getNome()); // Adiciona apenas o nome do prato
        }
    }

    private void carregarBebidas() {
        BebidaController bebidaController = new BebidaController(); // Controlador para bebidas
        List<Bebida> bebidas = bebidaController.listarBebidas(); // Supondo que você tenha esse método

        // Adiciona bebidas na lista
        for (Bebida bebida : bebidas) {
            bebidasListModel.addElement(bebida.getNome()); // Adiciona apenas o nome da bebida
        }
    }

    private class SelecionarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pratoSelecionado = pratosList.getSelectedValue();
            String bebidaSelecionada = bebidasList.getSelectedValue();

            if (pratoSelecionado != null) {
                mesaDetalheView.adicionarPratoNaComanda(pratoSelecionado); // Adiciona o prato à comanda
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um prato.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

            if (bebidaSelecionada != null) {
                mesaDetalheView.adicionarBebidaNaComanda(bebidaSelecionada); // Adiciona a bebida à comanda
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione uma bebida.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

            frame.dispose(); // Fecha a janela de seleção
        }
    }
}
