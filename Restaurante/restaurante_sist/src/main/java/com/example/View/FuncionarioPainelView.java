package com.example.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioPainelView extends JFrame {
    private JButton btnCadastroMesa;
    private JButton btnMesaView;

    public FuncionarioPainelView() {
        setTitle("Painel do Funcionário");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnCadastroMesa = new JButton("Cadastro de Mesa");
        btnMesaView = new JButton("Ver Mesas");

        JPanel panel = new JPanel();
        panel.add(btnCadastroMesa);
        panel.add(btnMesaView);

        add(panel);
        setVisible(true);

        btnCadastroMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroMesaView();
            }
        });

        btnMesaView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MesaView();
            }
        });
    }
}
