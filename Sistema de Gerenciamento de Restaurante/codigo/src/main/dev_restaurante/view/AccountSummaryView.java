package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class AccountSummaryView extends JFrame {
    private JLabel totalPedidoLabel;
    private JLabel taxaEntregaLabel;
    private JButton continuarButton;
    private JButton voltarButton;

    public AccountSummaryView(double totalPedido, double taxaEntrega) {
        setTitle("Delivery - Conta ");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Conta - Pedido", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        totalPedidoLabel = new JLabel("Total do Pedido: R$ " + String.format("%.2f", totalPedido), JLabel.CENTER);
        taxaEntregaLabel = new JLabel("Taxa de Entrega: R$ " + String.format("%.2f", taxaEntrega), JLabel.CENTER);

        continuarButton = new JButton("Continuar");
        voltarButton = new JButton("Voltar");

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(totalPedidoLabel);
        infoPanel.add(taxaEntregaLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(continuarButton);
        buttonPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getContinuarButton() {
        return continuarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}