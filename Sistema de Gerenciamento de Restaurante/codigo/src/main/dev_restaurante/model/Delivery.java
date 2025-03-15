package dev_restaurante.model;public class Delivery {
    private Cliente cliente;
    private String endereco;
    private String status;
    private boolean distanciaSuperior3KM;
    private Pedido pedido;

    public Delivery(Cliente cliente, String endereco, boolean distanciaSuperior3KM) {
        this.cliente = cliente;
        this.endereco = endereco;
        this.status = "Pendente"; 
        this.distanciaSuperior3KM = distanciaSuperior3KM;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDistanciaSuperior3KM() {
        return distanciaSuperior3KM;
    }

    public void setDistanciaSuperior3KM(boolean distanciaSuperior3KM) {
        this.distanciaSuperior3KM = distanciaSuperior3KM;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}