package main;

import dev_restaurante.controller.ReceptionController;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.ReceptionView;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        ReceptionView receptionView = new ReceptionView();
        new ReceptionController(restaurante, receptionView);
        receptionView.setVisible(true);
    }
}
