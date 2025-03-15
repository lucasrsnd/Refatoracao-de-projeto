package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class AddDeliveryView extends JFrame {
    private JTextField nomeClienteField;
    private JRadioButton distanciaSimRadioButton;
    private JRadioButton distanciaNaoRadioButton;
    private JButton adicionarButton;
    private JButton voltarButton;

    public AddDeliveryView() {
        setTitle("Pedido - Delivery");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Criando Pedido", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel nomeClienteLabel = new JLabel("Nome:");
        nomeClienteField = new JTextField(20);  

        JSeparator separator = new JSeparator();

        JLabel distanciaLabel = new JLabel("A distância de entrega é superior a 3 KM?", JLabel.CENTER);
        distanciaSimRadioButton = new JRadioButton("Sim");
        distanciaNaoRadioButton = new JRadioButton("Não");
        ButtonGroup distanciaButtonGroup = new ButtonGroup();
        distanciaButtonGroup.add(distanciaSimRadioButton);
        distanciaButtonGroup.add(distanciaNaoRadioButton);

        JPanel nomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nomePanel.add(nomeClienteLabel);
        nomePanel.add(nomeClienteField);

        JPanel distanciaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        distanciaPanel.add(distanciaSimRadioButton);
        distanciaPanel.add(distanciaNaoRadioButton);

        adicionarButton = new JButton("Adicionar");
        voltarButton = new JButton("Voltar");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(nomePanel);
        formPanel.add(separator);
        formPanel.add(distanciaLabel);
        formPanel.add(distanciaPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarButton);
        buttonPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getNomeClienteField() {
        return nomeClienteField;
    }

    public JRadioButton getDistanciaSimRadioButton() {
        return distanciaSimRadioButton;
    }

    public JRadioButton getDistanciaNaoRadioButton() {
        return distanciaNaoRadioButton;
    }

    public JButton getAdicionarButton() {
        return adicionarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}