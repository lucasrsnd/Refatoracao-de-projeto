package dev_restaurante.controller;

import dev_restaurante.model.Cliente;
import dev_restaurante.model.Delivery;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.AddDeliveryView;
import dev_restaurante.view.DeliveryView;
import dev_restaurante.view.MenuDeliveryView;
import dev_restaurante.view.ReceptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class DeliveryController {
    private Restaurante restaurante;
    private DeliveryView deliveryView;
    private AddDeliveryView addDeliveryView;
    private ReceptionView receptionView;

    public DeliveryController(Restaurante restaurante, DeliveryView deliveryView, ReceptionView receptionView) {
        this.restaurante = restaurante;
        this.deliveryView = deliveryView;
        this.receptionView = receptionView;

        this.deliveryView.getAdicionarPedidoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirAdicionarPedido();
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao abrir a tela de adicionar pedido: " + ex.getMessage());
                }
            }
        });

        this.deliveryView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deliveryView.setVisible(false);
                    receptionView.setVisible(true);
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao voltar para a recepção: " + ex.getMessage());
                }
            }
        });
    }

    private void abrirAdicionarPedido() {
        addDeliveryView = new AddDeliveryView();
        new AddDeliveryController(restaurante, addDeliveryView, deliveryView);
        addDeliveryView.setVisible(true);
        deliveryView.setVisible(false);
    }

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
                abrirMenuDelivery(delivery);
            } catch (Exception ex) {
                mostrarMensagemErro("Erro ao criar o pedido de delivery: " + ex.getMessage());
            }
        }

        private void abrirMenuDelivery(Delivery delivery) {
            try {
                MenuDeliveryView menuDeliveryView = new MenuDeliveryView();
                new MenuDeliveryController(restaurante, delivery, menuDeliveryView, deliveryView);
                menuDeliveryView.setVisible(true);
                addDeliveryView.setVisible(false);
            } catch (Exception ex) {
                mostrarMensagemErro("Erro ao abrir o menu de delivery: " + ex.getMessage());
            }
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}