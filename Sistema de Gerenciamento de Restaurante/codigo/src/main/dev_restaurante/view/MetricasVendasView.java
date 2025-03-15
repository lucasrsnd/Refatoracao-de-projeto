package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class MetricasVendasView extends JFrame {
    private JPanel mainPanel;
    private JButton voltarButton;

    public MetricasVendasView() {
        setTitle("Métricas de Vendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        voltarButton = new JButton("Voltar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(voltarButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}