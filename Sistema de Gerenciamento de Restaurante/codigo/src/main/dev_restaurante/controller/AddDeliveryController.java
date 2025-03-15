package dev_restaurante.controller;

import dev_restaurante.model.Cliente;
import dev_restaurante.model.Delivery;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.AddDeliveryView;
import dev_restaurante.view.DeliveryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDeliveryController {
    private Restaurante restaurante;
    private AddDeliveryView addDeliveryView;
    private DeliveryView deliveryView;

    public AddDeliveryController(Restaurante restaurante, AddDeliveryView addDeliveryView, DeliveryView deliveryView) {
        this.restaurante = restaurante;
        this.addDeliveryView = addDeliveryView;
        this.deliveryView = deliveryView;

        this.addDeliveryView.getAdicionarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionarPedido();
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao adicionar pedido: " + ex.getMessage());
                }
            }
        });

        this.addDeliveryView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addDeliveryView.setVisible(false);
                    deliveryView.setVisible(true);
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao voltar para a tela de delivery: " + ex.getMessage());
                }
            }
        });
    }

    private void adicionarPedido() {
        String nomeCliente = addDeliveryView.getNomeClienteField().getText();
        boolean distanciaSuperior3KM = addDeliveryView.getDistanciaSimRadioButton().isSelected();

        if (nomeCliente.isEmpty() || (!addDeliveryView.getDistanciaSimRadioButton().isSelected() && !addDeliveryView.getDistanciaNaoRadioButton().isSelected())) {
            JOptionPane.showMessageDialog(addDeliveryView, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Cliente cliente = new Cliente(nomeCliente, 1); 
            Delivery delivery = new Delivery(cliente, "Amazonas", distanciaSuperior3KM); 
            restaurante.adicionarDelivery(delivery);

            JOptionPane.showMessageDialog(addDeliveryView, "Pedido de delivery adicionado com sucesso!");

            addDeliveryView.getNomeClienteField().setText("");
            addDeliveryView.getDistanciaSimRadioButton().setSelected(false);
            addDeliveryView.getDistanciaNaoRadioButton().setSelected(false);
        } catch (Exception ex) {
            mostrarMensagemErro("Erro ao criar o pedido de delivery: " + ex.getMessage());
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}