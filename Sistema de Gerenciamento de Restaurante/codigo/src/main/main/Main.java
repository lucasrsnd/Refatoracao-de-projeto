package main;

import dev_restaurante.controller.ReceptionController;
import dev_restaurante.model.Restaurante;
import dev_restaurante.model.SistemaDeNotificacao;
import dev_restaurante.view.ReceptionView;

public class Main {
    public static void main(String[] args) {
        SistemaDeNotificacao sistemaDeNotificacao = new SistemaDeNotificacao();

        Restaurante restaurante = new Restaurante(sistemaDeNotificacao);
        ReceptionView receptionView = new ReceptionView();

        new ReceptionController(restaurante, receptionView, sistemaDeNotificacao);

        receptionView.setVisible(true);
    }
}
