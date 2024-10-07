package com.example.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RelatorioGenerator {
    private Connection connection; // Conexão com o banco de dados

    public RelatorioGenerator(Connection connection) {
        this.connection = connection;
    }

    public void gerarRelatorioFuncionarios(String caminhoArquivo) {
        String sql = "SELECT * FROM funcionarios"; // Altere para o nome correto da sua tabela de funcionários
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

            while (rs.next()) {
                String linha = "ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Cargo: " + rs.getString("cargo");
                writer.write(linha);
                writer.newLine();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Tratar exceções adequadamente em produção
        }
    }

    public void gerarRelatorioPratos(String caminhoArquivo) {
        String sql = "SELECT * FROM pratos"; // Altere para o nome correto da sua tabela de pratos
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

            while (rs.next()) {
                String linha = "ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Preço: " + rs.getDouble("preco");
                writer.write(linha);
                writer.newLine();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Tratar exceções adequadamente em produção
        }
    }
}
