package dev_restaurante.controller;

import dev_restaurante.model.Delivery;
import dev_restaurante.model.Item;
import dev_restaurante.model.Pedido;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.MenuDeliveryView;
import dev_restaurante.view.AccountSummaryView;
import dev_restaurante.view.DeliveryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuDeliveryController {
    private Restaurante restaurante;
    private Delivery delivery;
    private MenuDeliveryView menuDeliveryView;
    private DeliveryView deliveryView;

    public MenuDeliveryController(Restaurante restaurante, Delivery delivery, MenuDeliveryView menuDeliveryView, DeliveryView deliveryView) {
        this.restaurante = restaurante;
        this.delivery = delivery;
        this.menuDeliveryView = menuDeliveryView;
        this.deliveryView = deliveryView;

        this.menuDeliveryView.getAdicionarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionarPedido();
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao adicionar pedido: " + ex.getMessage());
                }
            }
        });

        this.menuDeliveryView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDeliveryView.setVisible(false);
                    deliveryView.setVisible(true);
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao voltar para a tela de delivery: " + ex.getMessage());
                }
            }
        });
    }

    private void adicionarPedido() {
        try {
            Map<String, Integer> itensQuantidades = new HashMap<>();

            JSpinner[] pratosQuantidades = menuDeliveryView.getPratosQuantidades();
            String[] pratos = {
                "Moqueca de Tilapia",
                "Falafel Assado",
                "Salada Primavera com Macarrão Konjac",
                "Escondidinho de Frango",
                "Strogonoff",
                "Cacarola de carne com legumes"
            };
            double[] pratosPrecos = {50.00, 20.00, 25.00, 35.00, 30.00, 40.00};

            for (int i = 0; i < pratos.length; i++) {
                int quantidade = (int) pratosQuantidades[i].getValue();
                if (quantidade > 0) {
                    itensQuantidades.put(pratos[i], quantidade);
                }
            }

            JSpinner[] bebidasQuantidades = menuDeliveryView.getBebidasQuantidades();
            String[] bebidas = {
                "Água",
                "Suco",
                "Refrigerante",
                "Cerveja",
                "Taça de vinho"
            };
            double[] bebidasPrecos = {5.00, 8.00, 8.00, 6.00, 18.00};

            for (int i = 0; i < bebidas.length; i++) {
                int quantidade = (int) bebidasQuantidades[i].getValue();
                if (quantidade > 0) {
                    itensQuantidades.put(bebidas[i], quantidade);
                }
            }

            Pedido pedido = new Pedido();
            for (Map.Entry<String, Integer> entry : itensQuantidades.entrySet()) {
                String itemNome = entry.getKey();
                int quantidade = entry.getValue();
                double preco = 0.0;

                for (int i = 0; i < pratos.length; i++) {
                    if (itemNome.equals(pratos[i])) {
                        preco = pratosPrecos[i];
                        break;
                    }
                }
                for (int i = 0; i < bebidas.length; i++) {
                    if (itemNome.equals(bebidas[i])) {
                        preco = bebidasPrecos[i];
                        break;
                    }
                }

                Item item = new Item(itemNome, preco);
                pedido.adicionarItem(item, quantidade);
            }

            delivery.setPedido(pedido);
            abrirFechamentoConta();
        } catch (NumberFormatException ex) {
            mostrarMensagemErro("Erro ao formatar preço: " + ex.getMessage());
        } catch (Exception ex) {
            mostrarMensagemErro("Erro ao adicionar itens ao pedido: " + ex.getMessage());
        }
    }

    private void abrirFechamentoConta() {
        try {
            double totalPedido = delivery.getPedido().getValorTotal();
            double taxaEntrega = delivery.isDistanciaSuperior3KM() ? 10.0 : 0.0; 
            AccountSummaryView accountSummaryView = new AccountSummaryView(totalPedido, taxaEntrega);
            new AccountSummaryController(restaurante, delivery, accountSummaryView, deliveryView);
            accountSummaryView.setVisible(true);
            menuDeliveryView.setVisible(false);
        } catch (Exception ex) {
            mostrarMensagemErro("Erro ao abrir a tela de resumo da conta: " + ex.getMessage());
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}