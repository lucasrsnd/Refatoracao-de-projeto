package dev_restaurante.view;

import dev_restaurante.model.Cliente;
import dev_restaurante.model.Mesa;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ViewClientsView extends JFrame {
    private JPanel mainPanel;
    private JButton adicionarPedidoButton;
    private JButton encerrarPedidoButton;
    private JButton finalizarPagamentoButton;
    private JButton voltarButton;
    private JComboBox<String> mesaComboBox;

    public ViewClientsView(List<Mesa> mesas) {
        setTitle("Visualizar Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        JPanel buttonPanel = new JPanel();
        adicionarPedidoButton = new JButton("Adicionar Pedido");
        encerrarPedidoButton = new JButton("Encerrar Pedido");
        finalizarPagamentoButton = new JButton("Finalizar Pagamento");
        voltarButton = new JButton("Voltar");

        buttonPanel.add(adicionarPedidoButton);
        buttonPanel.add(encerrarPedidoButton);
        buttonPanel.add(finalizarPagamentoButton);
        buttonPanel.add(voltarButton);

        String[] mesasArray = new String[mesas.size()];
        for (int i = 0; i < mesas.size(); i++) {
            mesasArray[i] = "Mesa " + (i + 1);
        }

        mesaComboBox = new JComboBox<>(mesasArray);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(mesaComboBox, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        atualizarMesas(mesas);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getAdicionarPedidoButton() {
        return adicionarPedidoButton;
    }

    public JButton getEncerrarPedidoButton() {
        return encerrarPedidoButton;
    }

    public JButton getFinalizarPagamentoButton() {
        return finalizarPagamentoButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }

    public int getMesaSelecionada() {
        return mesaComboBox.getSelectedIndex() + 1;
    }

    public void atualizarMesas(List<Mesa> mesas) {
        mainPanel.removeAll();

        JPanel panelMesas4 = new JPanel();
        panelMesas4.setLayout(new BoxLayout(panelMesas4, BoxLayout.Y_AXIS));
        panelMesas4.setBorder(BorderFactory.createTitledBorder("Mesas para até 4 pessoas"));

        JPanel panelMesas6 = new JPanel();
        panelMesas6.setLayout(new BoxLayout(panelMesas6, BoxLayout.Y_AXIS));
        panelMesas6.setBorder(BorderFactory.createTitledBorder("Mesas para até 6 pessoas"));

        JPanel panelMesas8 = new JPanel();
        panelMesas8.setLayout(new BoxLayout(panelMesas8, BoxLayout.Y_AXIS));
        panelMesas8.setBorder(BorderFactory.createTitledBorder("Mesas para até 8 pessoas"));

        for (Mesa mesa : mesas) {
            JPanel mesaPanel = new JPanel(new BorderLayout());
            mesaPanel.setBorder(BorderFactory.createTitledBorder("Mesa " + (mesas.indexOf(mesa) + 1)));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            StringBuilder sb = new StringBuilder();
            for (Cliente cliente : mesa.getClientes()) {
                sb.append("Cliente: ").append(cliente.getNome()).append(" - ").append(cliente.getQuantidadePessoas()).append(" pessoas\n");
            }
            textArea.setText(sb.toString());

            mesaPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

            if (mesa.getCapacidade() == 4) {
                panelMesas4.add(mesaPanel);
            } else if (mesa.getCapacidade() == 6) {
                panelMesas6.add(mesaPanel);
            } else if (mesa.getCapacidade() == 8) {
                panelMesas8.add(mesaPanel);
            }
        }

        mainPanel.add(panelMesas4);
        mainPanel.add(panelMesas6);
        mainPanel.add(panelMesas8);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}