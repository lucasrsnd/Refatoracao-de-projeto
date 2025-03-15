package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class MenuDeliveryView extends JFrame {
    private JButton adicionarButton;
    private JButton voltarButton;

    private JSpinner[] pratosQuantidades;
    private JSpinner[] bebidasQuantidades;

    public MenuDeliveryView() {
        setTitle("Delivery - Cardápio");
        setSize(1300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Cardápio do Delivery", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        String[] pratos = {
            "Moqueca de Tilapia - R$50,00",
            "Falafel Assado - R$20,00",
            "Salada Primavera com Macarrão Konjac - R$25,00",
            "Escondidinho de Frango - R$35,00",
            "Strogonoff - R$30,00",
            "Cacarola de carne com legumes - R$40,00"
        };

        String[] bebidas = {
            "Água - R$5,00",
            "Suco - R$8,00",
            "Refrigerante - R$8,00",
            "Cerveja - R$6,00",
            "Taça de vinho - R$18,00"
        };

        JPanel pratosPanel = new JPanel();
        pratosPanel.setLayout(new GridLayout(pratos.length, 2, 10, 10));
        pratosPanel.setBorder(BorderFactory.createTitledBorder("Pratos"));

        pratosQuantidades = new JSpinner[pratos.length];
        for (int i = 0; i < pratos.length; i++) {
            pratosPanel.add(new JLabel(pratos[i]));
            pratosQuantidades[i] = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            pratosQuantidades[i].setPreferredSize(new Dimension(50, 20));
            pratosPanel.add(pratosQuantidades[i]);
        }

        JPanel bebidasPanel = new JPanel();
        bebidasPanel.setLayout(new GridLayout(bebidas.length, 2, 10, 10));
        bebidasPanel.setBorder(BorderFactory.createTitledBorder("Bebidas"));

        bebidasQuantidades = new JSpinner[bebidas.length];
        for (int i = 0; i < bebidas.length; i++) {
            bebidasPanel.add(new JLabel(bebidas[i]));
            bebidasQuantidades[i] = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            bebidasQuantidades[i].setPreferredSize(new Dimension(50, 20));
            bebidasPanel.add(bebidasQuantidades[i]);
        }

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(pratosPanel);
        centerPanel.add(bebidasPanel);

        adicionarButton = new JButton("Adicionar");
        voltarButton = new JButton("Voltar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarButton);
        buttonPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JSpinner[] getPratosQuantidades() {
        return pratosQuantidades;
    }

    public JSpinner[] getBebidasQuantidades() {
        return bebidasQuantidades;
    }

    public JButton getAdicionarButton() {
        return adicionarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}