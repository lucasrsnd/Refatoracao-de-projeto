package dev_restaurante.view;


import java.awt.*;
import javax.swing.*;

public class DeliveryView extends JFrame {
    private JButton adicionarPedidoButton;
    private JButton voltarButton;

    public DeliveryView() {
        setTitle("Pedidos Delivery");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Pedidos Delivery", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        adicionarPedidoButton = new JButton("Adicionar Pedido");
        voltarButton = new JButton("Voltar");

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        botoesPanel.add(adicionarPedidoButton);
        botoesPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(botoesPanel, BorderLayout.CENTER);
    }

    public JButton getAdicionarPedidoButton() {
        return adicionarPedidoButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}