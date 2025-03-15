package dev_restaurante.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Restaurante {
    private List<Mesa> mesas;
    private List<Cliente> filaDeEspera;
    private List<Delivery> deliverys;
    private List<PedidoFinalizado> pedidosFinalizados;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.filaDeEspera = new ArrayList<>();
        this.deliverys = new ArrayList<>();
        this.pedidosFinalizados = new ArrayList<>();
        inicializarMesas();
    }

    private void inicializarMesas() {
        // 4 mesas para até 4 pessoas
        mesas.addAll(IntStream.range(0, 4)
                              .mapToObj(i -> new Mesa(4))
                              .collect(Collectors.toList()));
        // 4 mesas para até 6 pessoas
        mesas.addAll(IntStream.range(0, 4)
                              .mapToObj(i -> new Mesa(6))
                              .collect(Collectors.toList()));
        // 2 mesas para até 8 pessoas
        mesas.addAll(IntStream.range(0, 2)
                              .mapToObj(i -> new Mesa(8))
                              .collect(Collectors.toList()));
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public List<Cliente> getFilaDeEspera() {
        return filaDeEspera;
    }

    public List<Delivery> getDeliverys() {
        return deliverys;
    }

    public List<PedidoFinalizado> getPedidosFinalizados() {
        return pedidosFinalizados;
    }

    public void adicionarDelivery(Delivery delivery) {
        this.deliverys.add(delivery);
    }

    public boolean adicionarCliente(Cliente cliente) {
        for (Mesa mesa : mesas) {
            if (mesa.getClientes().isEmpty() && mesa.getCapacidade() >= cliente.getQuantidadePessoas()) {
                mesa.adicionarCliente(cliente);
                return true;
            }
        }
        filaDeEspera.add(cliente);
        return false;
    }

    public void finalizarAtendimento(int numeroMesa, String metodoPagamento, double desconto, double valorFinal, int prazoRecebimento) {
        if (numeroMesa >= 1 && numeroMesa <= mesas.size()) {
            Mesa mesa = mesas.get(numeroMesa - 1);
            Pedido pedido = new Pedido();
            mesa.getPedidos().forEach(p -> pedido.adicionarItens(p.getItens()));
            mesa.finalizarPedido();
            mesa.getClientes().clear();
            pedidosFinalizados.add(new PedidoFinalizado(pedido, metodoPagamento, desconto, valorFinal, prazoRecebimento));
            realocarClientes();
        } else {
            throw new IllegalArgumentException("Número da mesa inválido");
        }
    }

    private void realocarClientes() {
        List<Cliente> clientesRealocados = filaDeEspera.stream()
            .filter(cliente -> mesas.stream().anyMatch(mesa -> mesa.getClientes().isEmpty() && mesa.getCapacidade() >= cliente.getQuantidadePessoas()))
            .collect(Collectors.toList());

        clientesRealocados.forEach(cliente -> {
            mesas.stream()
                .filter(mesa -> mesa.getClientes().isEmpty() && mesa.getCapacidade() >= cliente.getQuantidadePessoas())
                .findFirst()
                .ifPresent(mesa -> mesa.adicionarCliente(cliente));
        });

        filaDeEspera.removeAll(clientesRealocados);
    }

    public static class PedidoFinalizado {
        private Pedido pedido;
        private String metodoPagamento;
        private double desconto;
        private double valorFinal;
        private int prazoRecebimento;

        public PedidoFinalizado(Pedido pedido, String metodoPagamento, double desconto, double valorFinal, int prazoRecebimento) {
            this.pedido = pedido;
            this.metodoPagamento = metodoPagamento;
            this.desconto = desconto;
            this.valorFinal = valorFinal;
            this.prazoRecebimento = prazoRecebimento;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public String getMetodoPagamento() {
            return metodoPagamento;
        }

        public double getDesconto() {
            return desconto;
        }

        public double getValorFinal() {
            return valorFinal;
        }

        public int getPrazoRecebimento() {
            return prazoRecebimento;
        }
    }
}