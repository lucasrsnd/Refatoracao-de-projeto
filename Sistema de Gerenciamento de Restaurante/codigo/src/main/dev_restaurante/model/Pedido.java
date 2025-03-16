package dev_restaurante.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pedido {
    private List<Item> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item, int quantidade) {
        List<Item> novosItens = IntStream.range(0, quantidade)
                                         .mapToObj(i -> item)
                                         .collect(Collectors.toList());
        itens.addAll(novosItens);
    }

    public void adicionarItens(List<Item> itens) {
        this.itens.addAll(itens);
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return itens.stream()
                    .mapToDouble(Item::getPreco)
                    .sum();
    }
}