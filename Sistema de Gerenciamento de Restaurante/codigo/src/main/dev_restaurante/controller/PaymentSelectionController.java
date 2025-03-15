package dev_restaurante.controller;

import dev_restaurante.model.Delivery;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.PaymentSelectionView;
import dev_restaurante.view.DeliveryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentSelectionController {
    private Restaurante restaurante;
    private Delivery delivery;
    private PaymentSelectionView paymentSelectionView;
    private DeliveryView deliveryView;

    public PaymentSelectionController(Restaurante restaurante, Delivery delivery, PaymentSelectionView paymentSelectionView, DeliveryView deliveryView) {
        this.restaurante = restaurante;
        this.delivery = delivery;
        this.paymentSelectionView = paymentSelectionView;
        this.deliveryView = deliveryView;

        this.paymentSelectionView.getConfirmarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarPagamento();
            }
        });

        this.paymentSelectionView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentSelectionView.setVisible(false);
                deliveryView.setVisible(true);
            }
        });
    }

    private void confirmarPagamento() {
        double valorConta = delivery.getPedido().getValorTotal();
        if (delivery.isDistanciaSuperior3KM()) {
            valorConta += 10.0; // Adiciona taxa de entrega
        }

        double desconto = 0.0;
        int prazoRecebimento = 0;
        String metodoPagamento = (String) paymentSelectionView.getPagamentoComboBox().getSelectedItem();

        if (metodoPagamento.equals("Dinheiro")) {
            desconto = 0.0;
            prazoRecebimento = 0;
        } else if (metodoPagamento.equals("Pix")) {
            desconto = Math.min(valorConta * 0.0145, 10.0);
            prazoRecebimento = 0;
        } else if (metodoPagamento.equals("Débito")) {
            desconto = valorConta * 0.014;
            prazoRecebimento = 14;
        } else if (metodoPagamento.equals("Crédito")) {
            desconto = valorConta * 0.031;
            prazoRecebimento = 30;
        } else {
            JOptionPane.showMessageDialog(paymentSelectionView, "Por favor, selecione uma forma de pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valorFinal = valorConta - desconto;
        delivery.setStatus("Finalizado");
        JOptionPane.showMessageDialog(paymentSelectionView, "Pagamento confirmado!\nValor Final: R$ " + String.format("%.2f", valorFinal));

        restaurante.adicionarDelivery(delivery); // Adiciona o delivery ao restaurante com o status atualizado
        paymentSelectionView.setVisible(false);
        deliveryView.setVisible(true);
    }
}