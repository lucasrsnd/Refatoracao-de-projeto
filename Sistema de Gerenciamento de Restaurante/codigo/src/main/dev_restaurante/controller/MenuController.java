package dev_restaurante.controller;

import dev_restaurante.model.Item;
import dev_restaurante.model.Pedido;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.MenuView;
import dev_restaurante.view.ViewClientsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private Restaurante restaurante;
    private MenuView menuView;
    private ViewClientsView viewClientsView;
    private int numeroMesa;

    public MenuController(Restaurante restaurante, MenuView menuView, ViewClientsView viewClientsView, int numeroMesa) {
        this.restaurante = restaurante;
        this.menuView = menuView;
        this.viewClientsView = viewClientsView;
        this.numeroMesa = numeroMesa;

        this.menuView.getAdicionarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionarPedido();
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao adicionar pedido: " + ex.getMessage());
                }
            }
        });

        this.menuView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuView.setVisible(false);
                    viewClientsView.setVisible(true);
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao voltar para a visualização de clientes: " + ex.getMessage());
                }
            }
        });
    }

    private void adicionarPedido() {
        try {
            JPanel pratosPanel = menuView.getPratosPanel();
            JPanel bebidasPanel = menuView.getBebidasPanel();

            Pedido pedido = new Pedido();

            for (int i = 0; i < pratosPanel.getComponentCount(); i += 3) {
                JLabel nomeLabel = (JLabel) pratosPanel.getComponent(i);
                JLabel precoLabel = (JLabel) pratosPanel.getComponent(i + 1);
                JSpinner quantidadeSpinner = (JSpinner) pratosPanel.getComponent(i + 2);

                String nome = nomeLabel.getText();
                double preco = Double.parseDouble(precoLabel.getText().replace("R$ ", "").replace(",", "."));
                int quantidade = (Integer) quantidadeSpinner.getValue();

                if (quantidade > 0) {
                    pedido.adicionarItem(new Item(nome, preco), quantidade);
                }
            }

            for (int i = 0; i < bebidasPanel.getComponentCount(); i += 3) {
                JLabel nomeLabel = (JLabel) bebidasPanel.getComponent(i);
                JLabel precoLabel = (JLabel) bebidasPanel.getComponent(i + 1);
                JSpinner quantidadeSpinner = (JSpinner) bebidasPanel.getComponent(i + 2);

                String nome = nomeLabel.getText();
                double preco = Double.parseDouble(precoLabel.getText().replace("R$ ", "").replace(",", "."));
                int quantidade = (Integer) quantidadeSpinner.getValue();

                if (quantidade > 0) {
                    pedido.adicionarItem(new Item(nome, preco), quantidade);
                }
            }

            restaurante.getMesas().get(numeroMesa - 1).adicionarPedido(pedido);
            JOptionPane.showMessageDialog(menuView, "Pedido adicionado com sucesso!");

            menuView.setVisible(false);
            viewClientsView.setVisible(true);
            viewClientsView.atualizarMesas(restaurante.getMesas());
        } catch (NumberFormatException ex) {
            mostrarMensagemErro("Erro ao formatar preço: " + ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            mostrarMensagemErro("Erro ao acessar mesa: " + ex.getMessage());
        } catch (Exception ex) {
            mostrarMensagemErro("Erro inesperado: " + ex.getMessage());
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}