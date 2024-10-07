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

import com.example.Model.Bebida;
import com.example.Model.RestauranteConexao;

public class BebidaController {

    // Método para cadastrar uma nova bebida
    public boolean cadastrarBebida(Bebida bebida) {
        String sql = "INSERT INTO bebidas (nome, descricao, preco) VALUES (?, ?, ?)";

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, bebida.getNome());
            stmt.setString(2, bebida.getDescricao());
            stmt.setDouble(3, bebida.getPreco());

            stmt.executeUpdate();
            System.out.println("Bebida cadastrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar bebida: " + e.getMessage());
            return false;
        }
    }

    // Método para listar bebidas
    public List<Bebida> listarBebidas() {
        List<Bebida> bebidas = new ArrayList<>();
        String sql = "SELECT * FROM bebidas";

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");

                Bebida bebida = new Bebida(id, nome, descricao, preco);
                bebidas.add(bebida);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar bebidas: " + e.getMessage());
        }

        return bebidas;
    }

    // Método para gerar relatório de bebidas
    public void gerarRelatorioBebidas() {
        String sql = "SELECT * FROM bebidas";
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
            try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_bebidas.txt"))) {
                writer.print(relatorio.toString());
                System.out.println("Relatório de bebidas gerado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar o relatório: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de bebidas: " + e.getMessage());
        }
    }
    // Método para atualizar uma bebida existente
public boolean atualizarBebida(Bebida bebida) {
    String sql = "UPDATE bebidas SET nome = ?, descricao = ?, preco = ? WHERE id = ?";

    try (Connection connection = RestauranteConexao.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setString(1, bebida.getNome());
        stmt.setString(2, bebida.getDescricao());
        stmt.setDouble(3, bebida.getPreco());
        stmt.setInt(4, bebida.getId()); // ID da bebida a ser atualizada

        stmt.executeUpdate();
        System.out.println("Bebida atualizada com sucesso!");
        return true;

    } catch (SQLException e) {
        System.out.println("Erro ao atualizar bebida: " + e.getMessage());
        return false;
    }
}

// Método para excluir uma bebida
public boolean excluirBebida(int id) {
    String sql = "DELETE FROM bebidas WHERE id = ?";

    try (Connection connection = RestauranteConexao.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Bebida excluída com sucesso!");
        return true;

    } catch (SQLException e) {
        System.out.println("Erro ao excluir bebida: " + e.getMessage());
        return false;
    }
}

}
