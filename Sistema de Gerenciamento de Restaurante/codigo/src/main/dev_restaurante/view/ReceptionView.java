package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class ReceptionView extends JFrame {
    private JButton botaoAdicionarCliente;
    private JButton botaoVisualizarClientes;
    private JButton botaoFilaEspera;
    private JButton botaoMetricasVendas;
    private JButton botaoDelivery; 

    public ReceptionView() {
        setTitle("Recepção - Java Cooking");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel mensagemBoasVindas = new JLabel("Seja bem-vindo ao Java Cooking", JLabel.CENTER);
        mensagemBoasVindas.setFont(new Font("Arial", Font.BOLD, 24));

        botaoAdicionarCliente = new JButton("Adicionar Cliente");
        botaoVisualizarClientes = new JButton("Visualizar Clientes");
        botaoFilaEspera = new JButton("Fila de Espera");
        botaoMetricasVendas = new JButton("Métricas de Vendas");
        botaoDelivery = new JButton("Delivery"); 

        Dimension buttonSize = new Dimension(200, 50);
        botaoAdicionarCliente.setPreferredSize(buttonSize);
        botaoVisualizarClientes.setPreferredSize(buttonSize);
        botaoFilaEspera.setPreferredSize(buttonSize);
        botaoMetricasVendas.setPreferredSize(buttonSize);
        botaoDelivery.setPreferredSize(buttonSize); 

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        botoesPanel.add(botaoAdicionarCliente);
        botoesPanel.add(botaoVisualizarClientes);
        botoesPanel.add(botaoFilaEspera);
        botoesPanel.add(botaoMetricasVendas);
        botoesPanel.add(botaoDelivery); 

        setLayout(new BorderLayout());
        add(mensagemBoasVindas, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    public JButton getBotaoAdicionarCliente() {
        return botaoAdicionarCliente;
    }

    public JButton getBotaoVisualizarClientes() {
        return botaoVisualizarClientes;
    }

    public JButton getBotaoFilaEspera() {
        return botaoFilaEspera;
    }

    public JButton getBotaoMetricasVendas() {
        return botaoMetricasVendas;
    }

    public JButton getBotaoDelivery() {
        return botaoDelivery;
    }
}