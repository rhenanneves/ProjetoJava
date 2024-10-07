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

    public MesaView() {
        mesaController = new MesaController();
        frame = new JFrame("Gerenciar Mesas");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel de Mesas
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // 5 linhas, 2 colunas

        List<Mesa> mesas = mesaController.listarMesas();

        if (mesas != null && !mesas.isEmpty()) {
            for (int i = 0; i < mesas.size(); i++) {
                JButton mesaButton = new JButton("Mesa " + (i + 1));
                mesaButton.addActionListener(new MesaButtonListener(i + 1));
                updateMesaButtonColor(mesaButton, mesas.get(i));
                panel.add(mesaButton);
            }
        } else {
            System.out.println("Nenhuma mesa encontrada.");
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void updateMesaButtonColor(JButton mesaButton, Mesa mesa) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MesaView());
    }
    // Método na classe de interface do usuário
public void encerrarMesa(int mesaId) {
    // Verifique se a mesa existe antes de tentar encerrá-la
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
