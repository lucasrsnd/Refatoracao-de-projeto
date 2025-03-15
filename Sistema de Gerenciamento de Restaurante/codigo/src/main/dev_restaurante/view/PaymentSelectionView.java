package dev_restaurante.view;

import java.awt.*;
import javax.swing.*;

public class PaymentSelectionView extends JFrame {
    private JLabel valorContaLabel;
    private JComboBox<String> pagamentoComboBox;
    private JButton confirmarButton;
    private JButton voltarButton;

    public PaymentSelectionView(double valorConta) {
        setTitle("Selecionar Pagamento - Delivery");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Selecionar Pagamento", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        valorContaLabel = new JLabel("Valor da Conta: R$ " + String.format("%.2f", valorConta), JLabel.CENTER);

        String[] opcoesPagamento = {"Dinheiro", "Pix", "Débito", "Crédito"};
        pagamentoComboBox = new JComboBox<>(opcoesPagamento);

        confirmarButton = new JButton("Confirmar");
        voltarButton = new JButton("Voltar");

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(valorContaLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(pagamentoComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmarButton);
        buttonPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JLabel getValorContaLabel() {
        return valorContaLabel;
    }

    public JComboBox<String> getPagamentoComboBox() {
        return pagamentoComboBox;
    }

    public JButton getConfirmarButton() {
        return confirmarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}