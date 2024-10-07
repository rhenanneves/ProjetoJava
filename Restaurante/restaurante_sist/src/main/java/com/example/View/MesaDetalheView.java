package com.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.example.Controller.MesaController; // Import do controlador
import com.example.Model.Prato; // Import do modelo Prato

public class MesaDetalheView {
    private JFrame frame;
    private JList<String> itensComandaList; // Para exibir os itens da comanda
    private DefaultListModel<String> listModel;
    private MesaController mesaController;
    private int mesaId; // Armazena o ID da mesa
    private JLabel totalLabel; // Label para exibir o total

    public MesaDetalheView(int mesaId) {
        this.mesaId = mesaId; // Armazena o ID da mesa
        mesaController = new MesaController(); // Inicializa o controlador das mesas
        frame = new JFrame("Detalhes da Mesa");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Modelo da lista para exibir itens da comanda
        listModel = new DefaultListModel<>();
        itensComandaList = new JList<>(listModel);
        frame.add(new JScrollPane(itensComandaList), BorderLayout.CENTER); // Adiciona a lista na janela

        // Label para mostrar o total
        totalLabel = new JLabel("Total: R$ 0.00");
        frame.add(totalLabel, BorderLayout.SOUTH); // Adiciona o label na parte inferior

        // Botão para adicionar pratos
        JButton adicionarPratoButton = new JButton("Adicionar Prato");
        adicionarPratoButton.addActionListener(new AdicionarPratoListener());
        frame.add(adicionarPratoButton, BorderLayout.NORTH); // Adiciona o botão na parte superior

        // Botão para realizar o pagamento
        JButton pagamentoButton = new JButton("Realizar Pagamento");
        pagamentoButton.addActionListener(new PagamentoListener());
        frame.add(pagamentoButton, BorderLayout.EAST); // Adiciona o botão de pagamento na parte direita

        frame.setVisible(true);

        // Carregar itens existentes da comanda (se houver)
        carregarItensComanda();
    }

    private void carregarItensComanda() {
        // Carregar os itens da comanda da mesa aqui usando o mesaId
        List<Prato> pratosDaMesa = mesaController.obterPratosDaMesa(mesaId); // Método para obter os pratos
        if (pratosDaMesa != null) {
            for (Prato prato : pratosDaMesa) {
                listModel.addElement(prato.getNome()); // Adiciona o nome do prato à lista
            }
        }
        atualizarTotal(); // Atualiza o total ao carregar os pratos
    }

    private void atualizarTotal() {
        double total = calcularTotalComanda(); // Calcula o total
        totalLabel.setText(String.format("Total: R$ %.2f", total)); // Atualiza o texto do label
    }

    private double calcularTotalComanda() {
        double total = 0.0;
    
        // Acessar a lista de pratos da comanda
        for (int i = 0; i < listModel.getSize(); i++) {
            String pratoNome = listModel.getElementAt(i);
            
            // Verifique se o pratoNome não é nulo ou vazio
            if (pratoNome != null && !pratoNome.isEmpty()) {
                // Chame o método do controlador para obter o preço
                double precoPrato = mesaController.obterPrecoPrato(pratoNome); 
                total += precoPrato; // Adiciona o preço do prato ao total
            }
        }
        return total; // Retorna o total da comanda
    }
    
    
    private class AdicionarPratoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abre a janela de seleção de pratos
            new SelecionarPratoView(MesaDetalheView.this); // Passa a referência da MesaDetalheView
        }
    }

    private class PagamentoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Criar nova janela para escolher forma de pagamento
            JFrame pagamentoFrame = new JFrame("Escolha a Forma de Pagamento");
            pagamentoFrame.setSize(300, 150);
            pagamentoFrame.setLayout(new FlowLayout());

            // Botão para pagamento em Débito
            JButton debitoButton = new JButton("Débito");
            debitoButton.addActionListener(new FormasPagamentoListener("Débito", pagamentoFrame));
            pagamentoFrame.add(debitoButton);

            // Botão para pagamento em Crédito
            JButton creditoButton = new JButton("Crédito");
            creditoButton.addActionListener(new FormasPagamentoListener("Crédito", pagamentoFrame));
            pagamentoFrame.add(creditoButton);

            // Botão para pagamento em Dinheiro
            JButton dinheiroButton = new JButton("Dinheiro");
            dinheiroButton.addActionListener(new FormasPagamentoListener("Dinheiro", pagamentoFrame));
            pagamentoFrame.add(dinheiroButton);

            // Mostrar o total na janela de pagamento
            JLabel totalPagamentoLabel = new JLabel("Total: R$ " + calcularTotalComanda());
            pagamentoFrame.add(totalPagamentoLabel);

            pagamentoFrame.setVisible(true);
        }
    }

    private class FormasPagamentoListener implements ActionListener {
        private String formaPagamento;
        private JFrame pagamentoFrame;

        public FormasPagamentoListener(String formaPagamento, JFrame pagamentoFrame) {
            this.formaPagamento = formaPagamento; // Armazena a forma de pagamento escolhida
            this.pagamentoFrame = pagamentoFrame; // Armazena a referência da janela de pagamento
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Calcular o total da comanda
            double total = calcularTotalComanda();

            if ("Dinheiro".equalsIgnoreCase(formaPagamento)) {
                String valorRecebidoStr = JOptionPane.showInputDialog(frame, "Valor recebido:");
                double valorRecebido = Double.parseDouble(valorRecebidoStr);
                double troco = valorRecebido - total;

                // Exibir troco
                JOptionPane.showMessageDialog(frame, "Troco: R$ " + troco);
            }

            // Gerar relatório em TXT
            gerarRelatorio(total, formaPagamento);

            // Deletar a mesa (opcional, ajuste conforme necessário)
            mesaController.deletarMesa(mesaId);

            // Fechar a janela de pagamento
            pagamentoFrame.dispose();
            // Fechar a janela principal (opcional)
            frame.dispose();
        }

        private void gerarRelatorio(double total, String formaPagamento) {
            String nomeArquivo = "relatorio_mesa_" + mesaId + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                writer.write("Relatório da Comanda da Mesa " + mesaId + "\n");
                writer.write("Total: R$ " + total + "\n");
                writer.write("Forma de Pagamento: " + formaPagamento + "\n");
                writer.write("Itens da Comanda:\n");
                for (int i = 0; i < listModel.getSize(); i++) {
                    writer.write("- " + listModel.getElementAt(i) + "\n");
                }
                JOptionPane.showMessageDialog(frame, "Relatório gerado: " + nomeArquivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void adicionarPratoNaComanda(String pratoNome) {
        // Lógica para adicionar o prato à comanda
        listModel.addElement(pratoNome); // Adiciona o prato à lista da comanda
        atualizarTotal(); // Atualiza o total após adicionar um prato
        System.out.println("Prato adicionado à comanda: " + pratoNome);
    }
}
