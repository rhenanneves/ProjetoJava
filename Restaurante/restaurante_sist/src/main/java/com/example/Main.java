package com.example;


import java.sql.Connection;

import com.example.Model.RestauranteConexao;

public class Main {
    public static void main(String[] args) {
        // Testando a conexão com o banco
        Connection connection = RestauranteConexao.getConnection();
        if (connection != null) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            RestauranteConexao.closeConnection(connection);
        } else {
            System.out.println("Falha ao conectar ao banco de dados.");
        }
    }
}
