package dev_restaurante.controller;
import dev_restaurante.model.Cliente;
import dev_restaurante.model.Item;
import dev_restaurante.model.Mesa;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.ViewClientsView;
import dev_restaurante.view.ReceptionView;
import dev_restaurante.view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ViewClientsController {
    private Restaurante restaurante;
    private ViewClientsView viewClientsView;
    private ReceptionView receptionView;

    public ViewClientsController(Restaurante restaurante, ViewClientsView viewClientsView, ReceptionView receptionView) {
        this.restaurante = restaurante;
        this.viewClientsView = viewClientsView;
        this.receptionView = receptionView;

        this.viewClientsView.getAdicionarPedidoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPedido();
            }
        });

        this.viewClientsView.getFinalizarPagamentoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPagamento();
            }
        });

        this.viewClientsView.getEncerrarPedidoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarPedido();
            }
        });

        this.viewClientsView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewClientsView.setVisible(false);
                receptionView.setVisible(true);
            }
        });
    }

    private void adicionarPedido() {
        int numeroMesa = viewClientsView.getMesaSelecionada();
        if (numeroMesa > 0) {
            Mesa mesa = restaurante.getMesas().get(numeroMesa - 1);
            if (mesa.getClientes().isEmpty()) {
                JOptionPane.showMessageDialog(viewClientsView, "A mesa selecionada não possui um cliente alocado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Item> pratos = Arrays.asList(
                    new Item("Moqueca de Tilapia", 50.00),
                    new Item("Falafel Assado", 20.00),
                    new Item("Salada Primavera com Macarrão Konjac", 25.00),
                    new Item("Escondidinho de Frango", 35.00),
                    new Item("Strogonoff", 30.00),
                    new Item("Cacarola de carne com legumes", 40.00)
            );

            List<Item> bebidas = Arrays.asList(
                    new Item("Água", 5.00),
                    new Item("Suco", 8.00),
                    new Item("Refrigerante", 8.00),
                    new Item("Cerveja", 6.00),
                    new Item("Taça de vinho", 18.00)
            );

            MenuView menuView = new MenuView(pratos, bebidas);
            new MenuController(restaurante, menuView, viewClientsView, numeroMesa);
            menuView.setVisible(true);
            viewClientsView.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(viewClientsView, "Por favor, selecione uma mesa.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarPagamento() {
        int numeroMesa = viewClientsView.getMesaSelecionada();
        if (numeroMesa > 0) {
            Mesa mesa = restaurante.getMesas().get(numeroMesa - 1);
            if (mesa.getClientes().isEmpty()) {
                JOptionPane.showMessageDialog(viewClientsView, "A mesa selecionada não possui um cliente alocado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valorPedido = mesa.calcularTotalConta();
            double taxaServico = valorPedido * 0.10;
            double valorTotal = valorPedido + taxaServico;

            String[] opcoesPagamento = {"Dinheiro", "Pix", "Débito", "Crédito"};
            String metodoPagamento = (String) JOptionPane.showInputDialog(
                    viewClientsView,
                    "Selecione o método de pagamento:",
                    "Finalizar Pagamento",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoesPagamento,
                    opcoesPagamento[0]
            );

            if (metodoPagamento != null) {
                double desconto = 0;
                int prazo = 0;

                switch (metodoPagamento) {
                    case "Dinheiro":
                        desconto = 0;
                        prazo = 0;
                        break;
                    case "Pix":
                        desconto = Math.min(valorTotal * 0.0145, 10);
                        prazo = 0;
                        break;
                    case "Débito":
                        desconto = valorTotal * 0.014;
                        prazo = 14;
                        break;
                    case "Crédito":
                        desconto = valorTotal * 0.031;
                        prazo = 30;
                        break;
                }

                double valorFinal = valorTotal - desconto;
                int quantidadePessoas = mesa.getClientes().get(0).getQuantidadePessoas();
                double valorPorPessoa = valorFinal / quantidadePessoas;

                JOptionPane.showMessageDialog(viewClientsView,
                        String.format("Valor pedido: R$ %.2f%nDesconto: R$ %.2f%nValor total: R$ %.2f%nValor por pessoa: R$ %.2f%nPrazo de recebimento: %d dias",
                                valorPedido, desconto, valorFinal, valorPorPessoa, prazo),
                        "Conta da Mesa " + numeroMesa,
                        JOptionPane.INFORMATION_MESSAGE);

                restaurante.finalizarAtendimento(numeroMesa, metodoPagamento, desconto, valorFinal, prazo);
                atualizarVisualizacao();
            }
        } else {
            JOptionPane.showMessageDialog(viewClientsView, "Por favor, selecione uma mesa.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void encerrarPedido() {
        int numeroMesa = viewClientsView.getMesaSelecionada();
        if (numeroMesa > 0) {
            Mesa mesa = restaurante.getMesas().get(numeroMesa - 1);
            if (mesa.getClientes().isEmpty()) {
                JOptionPane.showMessageDialog(viewClientsView, "A mesa selecionada não possui um cliente alocado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valorPedido = mesa.calcularTotalConta();
            double taxaServico = valorPedido * 0.10;
            double valorTotal = valorPedido + taxaServico;
            int quantidadePessoas = mesa.getClientes().get(0).getQuantidadePessoas();
            double valorPorPessoa = valorTotal / quantidadePessoas;

            JOptionPane.showMessageDialog(viewClientsView,
                String.format("Valor pedido: R$ %.2f%nTaxa de serviço: R$ %.2f%nValor total: R$ %.2f%nValor por pessoa: R$ %.2f",
                valorPedido, taxaServico, valorTotal, valorPorPessoa),
                "Conta da Mesa " + numeroMesa,
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(viewClientsView, "Por favor, selecione uma mesa.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarVisualizacao() {
        viewClientsView.atualizarMesas(restaurante.getMesas());
    }
}