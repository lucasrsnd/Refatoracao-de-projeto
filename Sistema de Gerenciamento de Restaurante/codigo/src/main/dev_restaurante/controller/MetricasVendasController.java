package dev_restaurante.controller;

import dev_restaurante.model.Item;
import dev_restaurante.model.Pedido;
import dev_restaurante.model.Restaurante;
import dev_restaurante.model.Restaurante.PedidoFinalizado;
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

    public MetricasVendasController(Restaurante restaurante, MetricasVendasView metricasVendasView, ReceptionView receptionView) {
        this.restaurante = restaurante;
        this.metricasVendasView = metricasVendasView;
        this.receptionView = receptionView;

        this.metricasVendasView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metricasVendasView.setVisible(false);
                receptionView.setVisible(true);
            }
        });

        atualizarMetricas();
    }

    private void atualizarMetricas() {
        JPanel panel = metricasVendasView.getMainPanel();
        panel.removeAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        List<PedidoFinalizado> pedidosFinalizados = restaurante.getPedidosFinalizados();
        double totalVendas = 0;

        for (PedidoFinalizado pedidoFinalizado : pedidosFinalizados) {
            Pedido pedido = pedidoFinalizado.getPedido();
            double valorFinal = pedidoFinalizado.getValorFinal();
            totalVendas += valorFinal;

            JPanel pedidoPanel = new JPanel(new BorderLayout());
            pedidoPanel.setBorder(BorderFactory.createTitledBorder("Pedido Finalizado"));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(
 
                "Método de Pagamento: " + pedidoFinalizado.getMetodoPagamento() + "\n" +
                "Prazo de Recebimento: " + pedidoFinalizado.getPrazoRecebimento() + " dias\n" +
                "Itens:\n"
            );

            List<Item> itens = pedido.getItens();
            for (Item item : itens) {
                textArea.append(item.getNome() + " - Quantidade: " + 1 + "\n");
            }

            textArea.append(
                "Valor do pedido: R$ " + String.format("%.2f", pedido.getValorTotal()) + "\n" +
                "Desconto: R$ " + String.format("%.2f", pedidoFinalizado.getDesconto()) + "\n" +
                "Valor final: R$ " + String.format("%.2f", valorFinal) + "\n"
            );

            pedidoPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            panel.add(pedidoPanel);
        }

        JTextArea totalVendasTextArea = new JTextArea("Total de vendas do dia: R$ " + String.format("%.2f", totalVendas));
        totalVendasTextArea.setEditable(false);
        panel.add(totalVendasTextArea);

        panel.revalidate();
        panel.repaint();
    }
}