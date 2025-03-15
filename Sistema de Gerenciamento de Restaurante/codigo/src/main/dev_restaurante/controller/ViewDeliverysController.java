package dev_restaurante.controller;

import dev_restaurante.model.Restaurante;
import dev_restaurante.view.ViewDeliverysView;
import dev_restaurante.view.DeliveryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDeliverysController {
    private Restaurante restaurante;
    private ViewDeliverysView viewDeliverysView;
    private DeliveryView deliveryView;

    public ViewDeliverysController(Restaurante restaurante, ViewDeliverysView viewDeliverysView, DeliveryView deliveryView) {
        this.restaurante = restaurante;
        this.viewDeliverysView = viewDeliverysView;
        this.deliveryView = deliveryView;

        this.viewDeliverysView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDeliverysView.setVisible(false);
                deliveryView.setVisible(true);
            }
        });
    }
}
