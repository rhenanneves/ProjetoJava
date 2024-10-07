package com.example.View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.example.Controller.PratoController; // Controlador para gerenciar os pratos
import com.example.Model.Prato;

public class SelecionarPratoView {
    private JFrame frame;
    private JList<String> pratosList;
    private DefaultListModel<String> listModel;
    private MesaDetalheView mesaDetalheView; // Referência para a view da mesa

    public SelecionarPratoView(MesaDetalheView mesaDetalheView) {
        this.mesaDetalheView = mesaDetalheView; // Armazena a referência da mesa
        frame = new JFrame("Selecionar Prato");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Modelo da lista para exibir os pratos
        listModel = new DefaultListModel<>();
        pratosList = new JList<>(listModel);
        frame.add(new JScrollPane(pratosList), BorderLayout.CENTER); // Adiciona a lista na janela

        // Botão para confirmar a seleção
        JButton selecionarButton = new JButton("Selecionar");
        selecionarButton.addActionListener(new SelecionarButtonListener());
        frame.add(selecionarButton, BorderLayout.SOUTH); // Adiciona o botão na parte inferior

        // Carregar pratos disponíveis
        carregarPratos();

        // Exibir a janela
        frame.setVisible(true);
    }

    private void carregarPratos() {
        PratoController pratoController = new PratoController(); // Controlador para pratos
        List<Prato> pratos = pratoController.listarPratos(); // Supondo que você tenha esse método

        // Adiciona pratos na lista
        for (Prato prato : pratos) {
            listModel.addElement(prato.getNome()); // Adiciona apenas o nome do prato
        }
    }

    private class SelecionarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pratoSelecionado = pratosList.getSelectedValue();
            if (pratoSelecionado != null) {
                mesaDetalheView.adicionarPratoNaComanda(pratoSelecionado); // Adiciona o prato à comanda
                frame.dispose(); // Fecha a janela de seleção
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um prato.");
            }
        }
    }
}
