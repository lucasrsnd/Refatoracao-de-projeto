package dev_restaurante.model;

import dev_restaurante.view.NotificacaoBarra;

public class SistemaDeNotificacao {
    private NotificacaoBarra notificacaoBarra;

    public SistemaDeNotificacao() {
        
    }

    public void notificar(String mensagem) {
        if (notificacaoBarra == null) {
            notificacaoBarra = new NotificacaoBarra(mensagem);
        }
        notificacaoBarra.exibirNotificacao();
    }
}
