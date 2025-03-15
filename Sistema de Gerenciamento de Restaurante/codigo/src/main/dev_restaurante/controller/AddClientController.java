package dev_restaurante.controller;
import dev_restaurante.model.Cliente;
import dev_restaurante.model.Restaurante;
import dev_restaurante.view.AddClientView;
import dev_restaurante.view.ReceptionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientController {
    private Restaurante restaurante;
    private AddClientView addClientView;
    private ReceptionView receptionView;

    public AddClientController(Restaurante restaurante, AddClientView addClientView, ReceptionView receptionView) {
        this.restaurante = restaurante;
        this.addClientView = addClientView;
        this.receptionView = receptionView;

        this.addClientView.getAdicionarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionarCliente();
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao adicionar cliente: " + ex.getMessage());
                }
            }
        });

        this.addClientView.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addClientView.setVisible(false);
                    receptionView.setVisible(true);
                } catch (Exception ex) {
                    mostrarMensagemErro("Erro ao voltar para a recepção: " + ex.getMessage());
                }
            }
        });
    }

    private void adicionarCliente() {
        String nome = addClientView.getNomeField().getText();
        int quantidadePessoas;
        try {
            quantidadePessoas = Integer.parseInt(addClientView.getQuantidadePessoasField().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(addClientView, "Por favor, insira um número válido para a quantidade de pessoas.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, quantidadePessoas);
        try {
            if (restaurante.adicionarCliente(cliente)) {
                JOptionPane.showMessageDialog(addClientView, "Cliente adicionado com sucesso!");
            } else if(quantidadePessoas <= 8){
                JOptionPane.showMessageDialog(addClientView, "Todas as mesas estão ocupadas. Cliente adicionado à fila de espera.");
            }else{
                JOptionPane.showMessageDialog(addClientView, "Erro ao adicionar cliente ao restaurante. Favor inserir número de pessoas valido");
            }
        } catch (Exception ex) {
            mostrarMensagemErro("Erro ao adicionar cliente ao restaurante: " + ex.getMessage());
        }

        addClientView.getNomeField().setText("");
        addClientView.getQuantidadePessoasField().setText("");
    }

    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}