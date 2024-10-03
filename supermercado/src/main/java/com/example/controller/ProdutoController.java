package com.example.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Produto;

public class ProdutoController {
    private Connection conn;

    public ProdutoController(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, codigo, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getCodigo());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());
            pstmt.executeUpdate();
        }
    }

    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
                produtos.add(produto);
            }
        }
        return produtos;
    }
    
    // Implementar métodos de atualizar e deletar se necessário
}
