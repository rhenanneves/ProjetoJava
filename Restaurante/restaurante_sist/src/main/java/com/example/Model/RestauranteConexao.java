package com.example.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RestauranteConexao {
    // URL de conexão com o PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurante_sist"; // Altere "nome_do_banco"
    private static final String USER = "postgres"; // Altere "seu_usuario"
    private static final String PASSWORD = "postgres"; // Altere "sua_senha"

    // Método para obter a conexão
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }

    // Método para fechar a conexão
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
