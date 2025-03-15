package dev_restaurante.model;

public class Cliente {
    private String nome;
    private int quantidadePessoas;

    public Cliente(String nome, int quantidadePessoas) {
        this.nome = nome;
        this.quantidadePessoas = quantidadePessoas;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }
}