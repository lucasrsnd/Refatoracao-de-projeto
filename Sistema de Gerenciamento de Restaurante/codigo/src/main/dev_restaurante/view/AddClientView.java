package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class AddClientView extends JFrame {
    private JTextField nomeField;
    private JTextField quantidadePessoasField;
    private JButton adicionarButton;
    private JButton voltarButton;

    public AddClientView() {
        setTitle("Adicionar Cliente");
        setSize(4000, 2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(nomeLabel, gbc);

        nomeField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);

        JLabel quantidadePessoasLabel = new JLabel("Quantidade de Pessoas:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(quantidadePessoasLabel, gbc);

        quantidadePessoasField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(quantidadePessoasField, gbc);

        adicionarButton = new JButton("Adicionar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(adicionarButton, gbc);

        voltarButton = new JButton("Voltar");
        gbc.gridx = 1;
        panel.add(voltarButton, gbc);

        add(panel);
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public JTextField getQuantidadePessoasField() {
        return quantidadePessoasField;
    }

    public JButton getAdicionarButton() {
        return adicionarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}