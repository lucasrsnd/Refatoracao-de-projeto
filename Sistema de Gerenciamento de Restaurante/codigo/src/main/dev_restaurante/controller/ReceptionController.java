package dev_restaurante.controller;

import dev_restaurante.model.Restaurante;
import dev_restaurante.view.ReceptionView;
import dev_restaurante.view.AddClientView;
import dev_restaurante.view.ViewClientsView;
import dev_restaurante.view.WaitingListView;
import dev_restaurante.view.MetricasVendasView;
import dev_restaurante.view.DeliveryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptionController {
    private Restaurante restaurante;
    private ReceptionView receptionView;

    public ReceptionController(Restaurante restaurante, ReceptionView receptionView) {
        this.restaurante = restaurante;
        this.receptionView = receptionView;

        this.receptionView.getBotaoAdicionarCliente().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receptionView.setVisible(false);
                AddClientView addClientView = new AddClientView();
                new AddClientController(restaurante, addClientView, receptionView);
                addClientView.setVisible(true);
            }
        });

        this.receptionView.getBotaoVisualizarClientes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receptionView.setVisible(false);
                ViewClientsView viewClientsView = new ViewClientsView(restaurante.getMesas());
                ViewClientsController viewClientsController = new ViewClientsController(restaurante, viewClientsView, receptionView);
                viewClientsController.atualizarVisualizacao();
                viewClientsView.setVisible(true);
            }
        });

        this.receptionView.getBotaoFilaEspera().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receptionView.setVisible(false);
                WaitingListView waitingListView = new WaitingListView(restaurante.getFilaDeEspera());
                WaitingListController waitingListController = new WaitingListController(restaurante, waitingListView, receptionView);
                waitingListController.atualizarVisualizacao();
                waitingListView.setVisible(true);
            }
        });

        this.receptionView.getBotaoMetricasVendas().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receptionView.setVisible(false);
                MetricasVendasView metricasVendasView = new MetricasVendasView();
                new MetricasVendasController(restaurante, metricasVendasView, receptionView);
                metricasVendasView.setVisible(true);
            }
        });

        this.receptionView.getBotaoDelivery().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receptionView.setVisible(false);
                DeliveryView deliveryView = new DeliveryView();
                new DeliveryController(restaurante, deliveryView, receptionView);
                deliveryView.setVisible(true);
            }
        });
    }
}