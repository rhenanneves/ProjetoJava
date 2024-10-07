package com.example.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Bebida;
import com.example.Model.Mesa;
import com.example.Model.Prato;
import com.example.Model.RestauranteConexao;

public class MesaController {

    // Método para abrir uma mesa
    public boolean abrirMesa(int id) {
        String sql = "UPDATE mesas SET status = 'ocupada' WHERE id = ?";

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Mesa " + id + " aberta com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao abrir mesa: " + e.getMessage());
            return false;
        }
    }

    // Método para listar mesas
    public List<Mesa> listarMesas() {
        String sql = "SELECT * FROM mesas"; // Ajuste a tabela de acordo com seu banco
        List<Mesa> mesas = new ArrayList<>(); // Usando uma lista para armazenar mesas

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                int numero = rs.getInt("numero"); // Obtendo o número da mesa do banco de dados
                mesas.add(new Mesa(id, status, numero)); // Adiciona a mesa à lista
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar mesas: " + e.getMessage());
        }

        return mesas; // Retorna a lista de mesas
    }

    // Método para cadastrar uma nova mesa
    public boolean cadastrarMesa(Mesa mesa) {
        String sql = "INSERT INTO mesas (id, status, numero) VALUES (?, ?, ?)"; // Certifique-se de que o campo 'numero'
                                                                                // está aqui

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, mesa.getId());
            stmt.setString(2, mesa.getStatus());
            stmt.setInt(3, mesa.getNumero()); // Passando o número da mesa
            stmt.executeUpdate();
            System.out.println("Mesa " + mesa.getId() + " cadastrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar mesa: " + e.getMessage());
            return false;
        }
    }

    // Método para deletar uma mesa
    public boolean deletarMesa(int id) {
        String sql = "DELETE FROM mesas WHERE id = ?";

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mesa " + id + " deletada com sucesso!");
                return true;
            } else {
                System.out.println("Nenhuma mesa encontrada com o ID " + id);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar mesa: " + e.getMessage());
            return false;
        }
    }

    // Método para obter pratos associados à mesa
    public List<Prato> obterPratosDaMesa(int mesaId) {
        String sql = "SELECT * FROM pratos WHERE mesa_id = ?";
        List<Prato> pratos = new ArrayList<>();

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, mesaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Criar uma nova instância de Prato e adicionar à lista
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao"); // Adicionando a descrição
                double preco = rs.getDouble("preco"); // Adicionando o preço
                Prato prato = new Prato(id, nome, descricao, preco); // Usando o construtor correto
                pratos.add(prato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pratos;
    }

    // Método para calcular o total da comanda (pratos e bebidas)
    public double calcularTotalComanda(int mesaId) {
        double total = 0.0;

        // Soma o preço dos pratos
        List<Prato> pratos = obterPratosDaMesa(mesaId);
        for (Prato prato : pratos) {
            total += prato.getPreco();
        }

        // Soma o preço das bebidas
        List<Bebida> bebidas = obterBebidasDaMesa(mesaId);
        for (Bebida bebida : bebidas) {
            total += bebida.getPreco();
        }

        return total; // Retorna o total de pratos e bebidas
    }

    public double obterPrecoPrato(String pratoNome) {
        String sql = "SELECT preco FROM pratos WHERE nome = ?";
        double preco = 0.0;

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pratoNome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                preco = rs.getDouble("preco"); // Obtém o preço do prato
            } else {
                System.out.println("Prato não encontrado: " + pratoNome);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter preço do prato: " + e.getMessage());
        }
        return preco; // Retorna o preço ou 0 se não encontrado
    }

    // Método para encerrar e deletar uma mesa
    public boolean encerrarMesa(int id) {
        // Aqui, você poderia incluir a lógica para processar o pagamento
        // Vamos assumir que você tem um método para processar o pagamento
        boolean pagamentoRealizado = processarPagamento(id); // Exemplo de método para processar pagamento

        if (pagamentoRealizado) {
            // Se o pagamento for realizado, vamos deletar a mesa
            return deletarMesa(id); // Chama o método de deletar mesa
        } else {
            System.out.println("Erro ao processar pagamento para a mesa " + id);
            return false; // Retorna false se o pagamento não foi realizado
        }
    }

    // Método simulado para processar o pagamento
    private boolean processarPagamento(int mesaId) {
        // Lógica para processar o pagamento da comanda da mesa
        // Retorna true se o pagamento foi bem-sucedido, caso contrário, retorna false
        // Aqui você poderia incluir a lógica de calcular o total e confirmar o
        // pagamento

        double total = calcularTotalComanda(mesaId); // Certifique-se de que esse método é acessível
        System.out.println("Total a pagar: " + total);
        // Simule o pagamento com uma condição (aqui você pode colocar sua lógica de
        // pagamento real)
        return true; // Retorna true para simulação, mude conforme necessário
    }

    public Mesa buscarMesaPorId(int id) {
        String sql = "SELECT * FROM mesas WHERE id = ?";

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Se a mesa existir, crie e retorne uma nova instância de Mesa
                int numero = rs.getInt("numero");
                String status = rs.getString("status");
                return new Mesa(id, status, numero); // Retorna a mesa encontrada
            } else {
                return null; // Retorna null se a mesa não for encontrada
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar mesa: " + e.getMessage());
            return null; // Retorna null em caso de erro
        }
    }

    // Método para obter bebidas associadas à mesa
    public List<Bebida> obterBebidasDaMesa(int mesaId) {
        String sql = "SELECT * FROM bebidas WHERE mesa_id = ?"; // Supondo que há uma chave estrangeira mesa_id
        List<Bebida> bebidas = new ArrayList<>();

        try (Connection connection = RestauranteConexao.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, mesaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                Bebida bebida = new Bebida(id, nome, descricao, preco);
                bebidas.add(bebida);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar bebidas da mesa: " + e.getMessage());
        }

        return bebidas;
    }

    public void gerarRelatorioMesa(int mesaId) {
        Mesa mesa = buscarMesaPorId(mesaId);
        
        if (mesa == null) {
            System.out.println("Mesa não encontrada!");
            return;
        }
    
        System.out.println("Relatório da Mesa " + mesa.getNumero());
    
        // Listando pratos
        System.out.println("Pratos:");
        List<Prato> pratos = obterPratosDaMesa(mesaId);
        for (Prato prato : pratos) {
            System.out.println(" - " + prato.getNome() + ": R$ " + prato.getPreco());
        }
    
        // Listando bebidas
        System.out.println("Bebidas:");
        List<Bebida> bebidas = obterBebidasDaMesa(mesaId);
        for (Bebida bebida : bebidas) {
            System.out.println(" - " + bebida.getNome() + ": R$ " + bebida.getPreco());
        }
    
        // Total
        double total = calcularTotalComanda(mesaId);
        System.out.println("Total da Comanda: R$ " + total);
    }
    
}
