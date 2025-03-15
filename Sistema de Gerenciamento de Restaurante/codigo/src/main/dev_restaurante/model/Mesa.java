package dev_restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int capacidade;
    private List<Cliente> clientes;
    private List<Pedido> pedidos;

    public Mesa(int capacidade) {
        this.capacidade = capacidade;
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarCliente(Cliente cliente) {
        if (clientes.isEmpty() && cliente.getQuantidadePessoas() <= capacidade) {
            clientes.add(cliente);
        } else {
            throw new IllegalArgumentException("Capacidade da mesa excedida ou mesa já ocupada");
        }
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void adicionarItens(List<Item> itens) {
        Pedido pedido = new Pedido();
        itens.forEach(item -> pedido.adicionarItem(item, 1)); // Usando forEach com Stream
        pedidos.add(pedido);
    }

    public void finalizarPedido() {
        pedidos.clear();
    }

    public double calcularTotalConta() {
        // Usando stream para somar os valores dos pedidos
        return pedidos.stream()
                      .mapToDouble(Pedido::getValorTotal)
                      .sum();
    }
}