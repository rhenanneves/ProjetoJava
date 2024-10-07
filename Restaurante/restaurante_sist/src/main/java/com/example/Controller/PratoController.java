package com.example.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Prato;
import com.example.Model.RestauranteConexao;

public class PratoController {

    // Método para cadastrar um novo prato
    public boolean cadastrarPrato(Prato prato) {
        String sql = "INSERT INTO pratos (nome, descricao, preco) VALUES (?, ?, ?)";

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());

            stmt.executeUpdate();
            System.out.println("Prato cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar prato: " + e.getMessage());
            return false;
        }
    }

    // Método para gerar relatório de pratos
    public void gerarRelatorioPratos() {
        String sql = "SELECT * FROM pratos";
        StringBuilder relatorio = new StringBuilder();

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                relatorio.append("Nome: ").append(nome)
                        .append(", Descrição: ").append(descricao)
                        .append(", Preço: ").append(preco).append("\n");
            }

            // Salvar em arquivo de texto
            try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_pratos.txt"))) {
                writer.print(relatorio.toString());
                System.out.println("Relatório de pratos gerado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar o relatório: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de pratos: " + e.getMessage());
        }
    }

    // Método para gerar relatório de vendas
    public void gerarRelatorioVendas() {
        String sql = "SELECT * FROM vendas"; // Altere para a consulta que você precisa
        StringBuilder relatorio = new StringBuilder();

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Ajuste conforme a estrutura da sua tabela de vendas
                int mesaId = rs.getInt("mesa_id");
                String pratoNome = rs.getString("prato_nome");
                double preco = rs.getDouble("preco");
                relatorio.append("Mesa: ").append(mesaId)
                        .append(", Prato: ").append(pratoNome)
                        .append(", Preço: ").append(preco).append("\n");
            }

            // Salvar em arquivo de texto
            try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_vendas.txt"))) {
                writer.print(relatorio.toString());
                System.out.println("Relatório de vendas gerado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar o relatório: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de vendas: " + e.getMessage());
        }
    }

    // Método para registrar venda
    public boolean registrarVenda(int mesaId, String pratoNome, double preco) {
        String sql = "INSERT INTO vendas (mesa_id, prato_nome, preco) VALUES (?, ?, ?)";

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, mesaId);
            stmt.setString(2, pratoNome);
            stmt.setDouble(3, preco);
            stmt.executeUpdate();
            System.out.println("Venda registrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
            return false;
        }
    }

    // Método para listar pratos
   // Método para listar pratos
public List<Prato> listarPratos() {
    List<Prato> pratos = new ArrayList<>();
    String sql = "SELECT * FROM pratos";

    try (Connection connection = RestauranteConexao.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Criando uma nova instância de Prato utilizando o construtor que aceita todos os parâmetros
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
            
                // Instanciando o prato com todos os parâmetros
                Prato prato = new Prato(id, nome, descricao, preco);
                pratos.add(prato); // Adiciona o prato à lista
            }
            
            
    } catch (SQLException e) {
        System.out.println("Erro ao listar pratos: " + e.getMessage());
    }

    return pratos;
}

    
}
