package dev_restaurante.view;

import javax.swing.*;
import java.awt.*;

public class NotificacaoBarra extends JWindow {
    private JLabel notificacaoLabel;
    private Timer timer;

    public NotificacaoBarra(String mensagem) {
        setLayout(new BorderLayout());
        setSize(400, 50);
        setLocationRelativeTo(null);
        
        notificacaoLabel = new JLabel(mensagem, SwingConstants.CENTER);
        notificacaoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        notificacaoLabel.setForeground(Color.WHITE);
        notificacaoLabel.setBackground(new Color(0, 0, 0, 150));
        notificacaoLabel.setOpaque(true);
        
        add(notificacaoLabel, BorderLayout.CENTER);
        setBackground(new Color(0, 0, 0, 150));
    }

    public void exibirNotificacao() {
        setVisible(true);
        timer = new Timer(40000, e -> fecharNotificacao()); 
        timer.setRepeats(false);
        timer.start();
    }

    private void fecharNotificacao() {
        setVisible(false);
        timer.stop();
    }
}
