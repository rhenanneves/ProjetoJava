package com.example.View;

import javax.swing.*;
import java.awt.*;

public class RelatorioFuncionariosView {
    private JFrame frame;

    public RelatorioFuncionariosView() {
        frame = new JFrame("Relatório de Funcionários");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Aqui você pode adicionar componentes para exibir o relatório
        JTextArea textArea = new JTextArea();
        textArea.setText("Relatório de Funcionários:\n\n");
        // Aqui você deve implementar a lógica para preencher o relatório com dados reais

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> frame.dispose());
        frame.add(fecharButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
