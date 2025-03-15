package dev_restaurante.view;

import dev_restaurante.model.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewDeliverysView extends JFrame {
    private JButton voltarButton;
    private JPanel deliverysPanel;

    public ViewDeliverysView(List<Delivery> deliverys) {
        setTitle("Visualizar Deliverys");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Visualizar Deliverys", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        deliverysPanel = new JPanel();
        deliverysPanel.setLayout(new BoxLayout(deliverysPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(deliverysPanel);

        voltarButton = new JButton("Voltar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        atualizarDeliverys(deliverys);
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }

    public void atualizarDeliverys(List<Delivery> deliverys) {
        deliverysPanel.removeAll();
        for (Delivery delivery : deliverys) {
            JPanel deliveryPanel = new JPanel(new BorderLayout());
            deliveryPanel.setBorder(BorderFactory.createTitledBorder("Pedido Delivery"));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(
                "Cliente: " + delivery.getCliente().getNome() + "\n" +
                "Endereço: " + delivery.getEndereco() + "\n" +
                "Status: " + delivery.getStatus() + "\n"
            );

            deliveryPanel.add(textArea, BorderLayout.CENTER);
            deliverysPanel.add(deliveryPanel);
        }

        deliverysPanel.revalidate();
        deliverysPanel.repaint();
    }
}
