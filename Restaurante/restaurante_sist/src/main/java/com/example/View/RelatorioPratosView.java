package com.example.View;

import javax.swing.*;
import java.awt.*;

public class RelatorioPratosView {
    private JFrame frame;

    public RelatorioPratosView() {
        frame = new JFrame("Relatório de Pratos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Aqui você pode adicionar componentes para exibir o relatório
        JTextArea textArea = new JTextArea();
        textArea.setText("Relatório de Pratos:\n\n");
        // Aqui você deve implementar a lógica para preencher o relatório com dados reais

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> frame.dispose());
        frame.add(fecharButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
