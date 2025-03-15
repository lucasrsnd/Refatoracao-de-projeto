package dev_restaurante.controller;

import dev_restaurante.model.Cliente;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.WaitingListView;
import dev_restaurante.view.ReceptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WaitingListController {
    private Restaurante restaurante;
    private WaitingListView waitingListView;
    private ReceptionView receptionView;

    public WaitingListController(Restaurante restaurante, WaitingListView waitingListView, ReceptionView receptionView) {
        this.restaurante = restaurante;
        this.waitingListView = waitingListView;
        this.receptionView = receptionView;

        this.waitingListView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waitingListView.setVisible(false);
                receptionView.setVisible(true);
            }
        });

        atualizarVisualizacao();
    }

    public void atualizarVisualizacao() {
        List<Cliente> filaDeEspera = restaurante.getFilaDeEspera();
        waitingListView.atualizarFilaDeEspera(filaDeEspera);
    }
}
