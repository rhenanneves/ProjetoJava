package com.example.Controller;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.Model.Funcionario;
import com.example.Model.RestauranteConexao;

public class FuncionarioController {

    // Método para cadastrar um novo funcionário
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome, cargo, data_admissao, salario) VALUES (?, ?, ?, ?)";

        try (Connection connection = RestauranteConexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Formatar a data de string para java.sql.Date no formato dd/MM/yyyy
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAdmissaoSQL;
            try {
                java.util.Date parsedDate = dateFormat.parse(funcionario.getDataAdmissao());
                dataAdmissaoSQL = new Date(parsedDate.getTime());
            } catch (ParseException e) {
                System.out.println("Erro ao formatar a data: " + e.getMessage());
                return false;
            }

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setDate(3, dataAdmissaoSQL); // Definir a data no formato SQL
            stmt.setDouble(4, funcionario.getSalario());

            stmt.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
            return false;
        }
    }
}
