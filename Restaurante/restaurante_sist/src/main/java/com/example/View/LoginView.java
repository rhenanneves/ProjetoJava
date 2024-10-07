package com.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.Controller.LoginController;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginView() {
        // Configuração da janela
        setTitle("Tela de Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Definindo cores
        Color backgroundColor = new Color(240, 240, 240); // Cor de fundo
        Color buttonColor = new Color(51, 153, 255); // Cor do botão
        Color buttonHoverColor = buttonColor.darker(); // Cor do botão ao passar o mouse

        // Painel principal
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza os componentes

        // Adicionando componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Usuário:"), gbc);

        txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Senha:"), gbc);

        txtSenha = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtSenha, gbc);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(buttonColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa duas colunas
        panel.add(btnLogin, gbc);

        // Efeito hover para o botão
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(buttonHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(buttonColor);
            }
        });

        add(panel);

        // Ação do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                LoginController loginController = new LoginController();
                if (loginController.autenticarUsuario(usuario, senha)) {
                    if (loginController.isGerente(usuario)) {
                        new GerentePainelView();
                    } else {
                        new FuncionarioPainelView();
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView());
    }
}
