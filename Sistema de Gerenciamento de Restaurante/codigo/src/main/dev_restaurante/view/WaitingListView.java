package dev_restaurante.view;

import dev_restaurante.model.Cliente;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class WaitingListView extends JFrame {
    private JPanel mainPanel;
    private JButton voltarButton;

    public WaitingListView(List<Cliente> filaDeEspera) {
        setTitle("Fila de Espera");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        JPanel buttonPanel = new JPanel();
        voltarButton = new JButton("Voltar");
        buttonPanel.add(voltarButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        atualizarFilaDeEspera(filaDeEspera);
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }

    public void atualizarFilaDeEspera(List<Cliente> filaDeEspera) {
        mainPanel.removeAll();
        for (Cliente cliente : filaDeEspera) {
            JPanel clientePanel = new JPanel(new BorderLayout());
            clientePanel.setBorder(BorderFactory.createTitledBorder("Cliente"));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(
                    "Nome: " + cliente.getNome() + "\n" +
                    "Quantidade de Pessoas: " + cliente.getQuantidadePessoas()
            );

            clientePanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            mainPanel.add(clientePanel);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}