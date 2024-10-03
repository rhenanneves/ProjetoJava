package com.example.controller;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;

public class ClienteController {
    private Connection conn;

    public ClienteController(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, vip) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setBoolean(4, cliente.isVip());
            pstmt.executeUpdate();
        }
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getBoolean("vip")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
    // Implementar métodos de atualizar e deletar se necessário
}

