package dev_restaurante.controller;

import dev_restaurante.model.Delivery;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.AccountSummaryView;
import dev_restaurante.view.PaymentSelectionView;
import dev_restaurante.view.DeliveryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSummaryController {
    private Restaurante restaurante;
    private Delivery delivery;
    private AccountSummaryView accountSummaryView;
    private DeliveryView deliveryView;

    public AccountSummaryController(Restaurante restaurante, Delivery delivery, AccountSummaryView accountSummaryView, DeliveryView deliveryView) {
        this.restaurante = restaurante;
        this.delivery = delivery;
        this.accountSummaryView = accountSummaryView;
        this.deliveryView = deliveryView;

        this.accountSummaryView.getContinuarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirSelecaoPagamento();
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao abrir seleção de pagamento: " + ex.getMessage());
                }
            }
        });

        this.accountSummaryView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    accountSummaryView.setVisible(false);
                    deliveryView.setVisible(true);
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao voltar para a tela de entrega: " + ex.getMessage());
                }
            }
        });
    }

    private void abrirSelecaoPagamento() throws Exception {
        double valorConta = delivery.getPedido().getValorTotal();
        if (delivery.isDistanciaSuperior3KM()) {
            valorConta += 10.0; 
        }

        PaymentSelectionView paymentSelectionView = new PaymentSelectionView(valorConta);
        new PaymentSelectionController(restaurante, delivery, paymentSelectionView, deliveryView);
        paymentSelectionView.setVisible(true);
        accountSummaryView.setVisible(false);
    }

    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}