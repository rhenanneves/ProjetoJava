package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Funcionario;

public class FuncionarioController {
    private Connection conn;

    public FuncionarioController(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, funcao) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getFuncao());
            pstmt.executeUpdate();
        }
    }

    public List<Funcionario> listarFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("funcao")
                );
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }
    
    // Implementar métodos de atualizar e deletar se necessário
}
