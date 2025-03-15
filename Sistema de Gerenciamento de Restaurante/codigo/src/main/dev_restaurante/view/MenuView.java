package dev_restaurante.view;
import dev_restaurante.model.Item;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MenuView extends JFrame {
    private JPanel pratosPanel;
    private JPanel bebidasPanel;
    private JButton adicionarButton;
    private JButton voltarButton;

    public MenuView(List<Item> pratos, List<Item> bebidas) {
        setTitle("Cardápio");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel para os itens do cardápio
        pratosPanel = new JPanel(new GridLayout(pratos.size(), 3, 10, 10));
        bebidasPanel = new JPanel(new GridLayout(bebidas.size(), 3, 10, 10));

        // Adicionando os pratos
        for (Item prato : pratos) {
            JLabel nomeLabel = new JLabel(prato.getNome());
            JLabel precoLabel = new JLabel(String.format("R$ %.2f", prato.getPreco()));
            JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            Dimension spinnerSize = new Dimension(40, quantidadeSpinner.getPreferredSize().height);
            quantidadeSpinner.setPreferredSize(spinnerSize);

            pratosPanel.add(nomeLabel);
            pratosPanel.add(precoLabel);
            pratosPanel.add(quantidadeSpinner);
        }

        // Adicionando as bebidas
        for (Item bebida : bebidas) {
            JLabel nomeLabel = new JLabel(bebida.getNome());
            JLabel precoLabel = new JLabel(String.format("R$ %.2f", bebida.getPreco()));
            JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            Dimension spinnerSize = new Dimension(40, quantidadeSpinner.getPreferredSize().height);
            quantidadeSpinner.setPreferredSize(spinnerSize);

            bebidasPanel.add(nomeLabel);
            bebidasPanel.add(precoLabel);
            bebidasPanel.add(quantidadeSpinner);
        }

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        adicionarButton = new JButton("Adicionar");
        voltarButton = new JButton("Voltar");
        buttonPanel.add(adicionarButton);
        buttonPanel.add(voltarButton);

        // Configurando o layout da janela
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Adicionando título e separador para Pratos
        JPanel pratosTitlePanel = new JPanel(new BorderLayout());
        JLabel pratosLabel = new JLabel("Pratos", JLabel.LEFT);
        pratosLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pratosTitlePanel.add(pratosLabel, BorderLayout.WEST);
        pratosTitlePanel.add(new JSeparator(), BorderLayout.SOUTH);
        contentPanel.add(pratosTitlePanel);

        // Adicionando painel de pratos
        contentPanel.add(pratosPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Adicionando separador e título para Bebidas
        contentPanel.add(new JSeparator());
        JPanel bebidasTitlePanel = new JPanel(new BorderLayout());
        JLabel bebidasLabel = new JLabel("Bebidas", JLabel.LEFT);
        bebidasLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bebidasTitlePanel.add(bebidasLabel, BorderLayout.WEST);
        contentPanel.add(bebidasTitlePanel);

        // Adicionando painel de bebidas
        contentPanel.add(bebidasPanel);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JPanel getPratosPanel() {
        return pratosPanel;
    }

    public JPanel getBebidasPanel() {
        return bebidasPanel;
    }

    public JButton getAdicionarButton() {
        return adicionarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }
}