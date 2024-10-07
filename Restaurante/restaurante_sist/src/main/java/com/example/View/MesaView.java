package com.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.Controller.MesaController;
import com.example.Model.Mesa;
import java.util.List;

public class MesaView {
    private JFrame frame;
    private MesaController mesaController;
    private JPanel panel; // Painel que contém os botões das mesas

    public MesaView() {
        mesaController = new MesaController();
        frame = new JFrame("Gerenciar Mesas");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel de Mesas
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas com espaçamento

        // Botão de Atualizar
        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new AtualizarButtonListener());

        // Botão para Abrir Mesa
        JButton abrirMesaButton = new JButton("Abrir Mesa");
        abrirMesaButton.addActionListener(new AbrirMesaButtonListener());

        // Adiciona os botões na parte superior
        JPanel buttonPanel = new JPanel(); // Painel para os botões
        buttonPanel.setLayout(new FlowLayout()); // Layout em linha
        buttonPanel.add(atualizarButton);
        buttonPanel.add(abrirMesaButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        // Carregar mesas
        carregarMesas();

        // Estilizando o painel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Bordas internas
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo do painel

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void carregarMesas() {
        panel.removeAll(); // Remove todos os botões antes de recarregar
        List<Mesa> mesas = mesaController.listarMesas();

        if (mesas != null && !mesas.isEmpty()) {
            for (int i = 0; i < mesas.size(); i++) {
                JButton mesaButton = new JButton("Mesa " + (i + 1));
                mesaButton.addActionListener(new MesaButtonListener(i + 1));
                updateMesaButtonAppearance(mesaButton, mesas.get(i));
                panel.add(mesaButton);
            }
        } else {
            JLabel noMesaLabel = new JLabel("Nenhuma mesa encontrada.", SwingConstants.CENTER);
            noMesaLabel.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(noMesaLabel);
        }

        panel.revalidate(); // Revalida o painel para mostrar as mudanças
        panel.repaint(); // Repinta o painel para mostrar as atualizações
    }

    private void updateMesaButtonAppearance(JButton mesaButton, Mesa mesa) {
        if (mesa != null) {
            switch (mesa.getStatus()) {
                case "livre":
                    mesaButton.setBackground(Color.GREEN);
                    break;
                case "reservada":
                    mesaButton.setBackground(Color.YELLOW);
                    break;
                case "ocupada":
                    mesaButton.setBackground(Color.RED);
                    break;
                default:
                    mesaButton.setBackground(Color.GRAY);
                    break;
            }
            mesaButton.setForeground(Color.BLACK); // Cor do texto
            mesaButton.setFont(new Font("Arial", Font.BOLD, 14)); // Estilizando a fonte
            mesaButton.setFocusPainted(false); // Removendo contorno ao clicar
            mesaButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // Borda do botão
        } else {
            mesaButton.setBackground(Color.GRAY);
        }
    }

    private class MesaButtonListener implements ActionListener {
        private int mesaId;

        public MesaButtonListener(int mesaId) {
            this.mesaId = mesaId;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Abre a nova tela de detalhes da mesa
            new MesaDetalheView(mesaId);
        }
    }

    // Listener para o botão de atualizar
    private class AtualizarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            carregarMesas(); // Atualiza a lista de mesas
        }
    }

    // Listener para o botão "Abrir Mesa"
    private class AbrirMesaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CadastroMesaView(); // Abre a tela de cadastro de mesa
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MesaView());
    }

    public void encerrarMesa(int mesaId) {
        Mesa mesa = mesaController.buscarMesaPorId(mesaId);
        if (mesa == null) {
            System.out.println("Mesa com ID " + mesaId + " não existe.");
            return;
        }

        if (mesaController.encerrarMesa(mesaId)) {
            System.out.println("Mesa " + mesaId + " encerrada e deletada com sucesso!");
        } else {
            System.out.println("Erro ao encerrar a mesa " + mesaId);
        }
    }
}
