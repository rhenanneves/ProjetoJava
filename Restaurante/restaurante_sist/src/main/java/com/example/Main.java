package com.example;

import com.example.View.LoginView;

public class Main {
    public static void main(String[] args) {
    // Abrindo a tela de Login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true); // Certifique-se de que LoginView é uma classe GUI Swing ou JavaFX
            }
        });
    }
}
