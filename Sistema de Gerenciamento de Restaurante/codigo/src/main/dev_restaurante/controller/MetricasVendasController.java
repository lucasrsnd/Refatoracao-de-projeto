package dev_restaurante.controller;

import dev_restaurante.model.Item;
import dev_restaurante.model.Pedido;
import dev_restaurante.model.Restaurante;
import dev_restaurante.model.SistemaDeNotificacao;
import dev_restaurante.view.MetricasVendasView;
import dev_restaurante.view.ReceptionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class MetricasVendasController {
    private Restaurante restaurante;
    private MetricasVendasView metricasVendasView;
    private ReceptionView receptionView;
    private SistemaDeNotificacao sistemaDeNotificacao;

    public MetricasVendasController(Restaurante restaurante, MetricasVendasView metricasVendasView, ReceptionView receptionView, SistemaDeNotificacao sistemaDeNotificacao) {
        this.restaurante = restaurante;
        this.metricasVendasView = metricasVendasView;
        this.receptionView = receptionView;
        this.sistemaDeNotificacao = sistemaDeNotificacao;

        // Ação do botão "Voltar"
        this.metricasVendasView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metricasVendasView.setVisible(false);
                receptionView.setVisible(true);
            }
        });

        // Atualiza as métricas de vendas ao inicializar o controller
        atualizarMetricas();
    }

    private void atualizarMetricas() {
        JPanel panel = metricasVendasView.getMainPanel();
        panel.removeAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        List<Pedido> pedidosFinalizados = restaurante.getPedidosFinalizados();  // Acessando diretamente os pedidos
        double totalVendas = 0;

        // Iterar sobre os pedidos finalizados e atualizar a tela de métricas
        for (Pedido pedido : pedidosFinalizados) {
            double valorFinal = pedido.getValorTotal();
            totalVendas += valorFinal;

            // Criar um painel para cada pedido finalizado
            JPanel pedidoPanel = new JPanel(new BorderLayout());
            pedidoPanel.setBorder(BorderFactory.createTitledBorder("Pedido Finalizado"));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(
                "Itens:\n"
            );

            // Exibir os itens do pedido
            List<Item> itens = pedido.getItens();
            for (Item item : itens) {
                textArea.append(item.getNome() + " - Quantidade: " + 1 + "\n");
            }

            textArea.append(
                "Valor do pedido: R$ " + String.format("%.2f", pedido.getValorTotal()) + "\n"
            );

            // Adicionar o texto no painel
            pedidoPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            panel.add(pedidoPanel);
        }

        // Mostrar o total de vendas do dia
        JTextArea totalVendasTextArea = new JTextArea("Total de vendas do dia: R$ " + String.format("%.2f", totalVendas));
        totalVendasTextArea.setEditable(false);
        panel.add(totalVendasTextArea);

        // Atualiza a tela com os novos dados
        panel.revalidate();
        panel.repaint();

        // Notificar o total de vendas ao atualizar as métricas
        sistemaDeNotificacao.notificar("Métricas de vendas atualizadas. Total de vendas: R$ " + String.format("%.2f", totalVendas));
    }
}
