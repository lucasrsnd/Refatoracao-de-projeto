# Alunos
- João Gabriel Maia da Costa
- Lucas Alves Resende

# Refatoração com o Padrão Observer no Sistema de Notificações

## Objetivo da Refatoração
O objetivo desta refatoração foi melhorar a modularidade e a flexibilidade do sistema de gerenciamento de pedidos. Anteriormente, o código estava fortemente acoplado, o que dificultava a adição de novas funcionalidades ou a modificação do comportamento do sistema sem afetar outras partes do código. Implementando o padrão de projeto Observer, conseguimos desacoplar a lógica de negócios da interface gráfica e garantir que diferentes componentes fossem notificados automaticamente quando um evento importante acontecesse, como o fechamento de um pedido.

## Padrão Observer
O Observer é um padrão comportamental que permite que um objeto (o publicador) notifique automaticamente seus observadores sobre mudanças de estado, sem a necessidade de uma dependência direta entre os componentes. No contexto do nosso sistema de restaurante, o objetivo é que o sistema de notificação seja acionado sempre que um pedido for finalizado, permitindo que a interface gráfica seja atualizada sem uma interação direta com a lógica de negócios.

# Alterações Realizadas
Criação da Classe SistemaDeNotificacao -> A primeira alteração foi a criação da classe SistemaDeNotificacao, que é responsável por gerenciar a notificação de eventos relevantes para a interface gráfica. Ela é instanciada sempre que um novo pedido é finalizado, e sua função principal é enviar uma mensagem de notificação para a interface gráfica, que será exibida para o usuário. A classe se beneficia do padrão Observer, permitindo que diferentes componentes possam "se inscrever" para receber notificações sem acoplamento direto.

 ```public class SistemaDeNotificacao {
    private NotificacaoBarra notificacaoBarra;
    private JFrame frame;

    public SistemaDeNotificacao(JFrame frame) {
        this.frame = frame;
    }

    public void notificar(String mensagem) {
        if (notificacaoBarra == null) {
            notificacaoBarra = new NotificacaoBarra(mensagem);
        }
        notificacaoBarra.exibirNotificacao();
    }
}
```

Modificação da Classe Restaurante -> A classe Restaurante foi adaptada para ser o publicador no contexto do padrão Observer. Ela agora possui uma instância de SistemaDeNotificacao, e sempre que um pedido for finalizado, ela notifica automaticamente todos os observadores registrados. Isso garante que o sistema de métricas de vendas e a interface gráfica possam ser atualizados sem a necessidade de chamadas diretas.

 ```public class Restaurante {
    private SistemaDeNotificacao sistemaDeNotificacao;

    public Restaurante(JFrame frame) {
        sistemaDeNotificacao = new SistemaDeNotificacao(frame);
    }

    public void finalizarPedido(Pedido pedido) {
        // Lógica para finalizar o pedido
        sistemaDeNotificacao.notificar("Pedido finalizado: " + pedido.getId());
    }
}
```

Atualização do Controller de Métricas de Vendas -> O controlador de métricas de vendas também foi alterado para ser um observador. Ele agora se inscreve nas notificações enviadas pela Restaurante e atualiza a interface de métricas sempre que um pedido for finalizado. Isso garante que as métricas de vendas sejam exibidas corretamente e atualizadas em tempo real, sem a necessidade de lógica extra no código da interface.

 ```public class MetricasVendasController implements Observer {
    public void atualizar(String mensagem) {
        // Atualiza as métricas de vendas com base na notificação
        System.out.println(mensagem);
    }
}
```

# Captura de Tela (após melhoria)

![imagem](/Sistema%20de%20Gerenciamento%20de%20Restaurante/docs/image/ImagemObserver.png);

# Correções e Melhorias Gerais
- Desacoplamento: As classes de interface gráfica agora não precisam mais depender diretamente da lógica de negócios. A classe Restaurante emite as notificações, enquanto os controladores, como o de métricas de vendas, se inscrevem para receber essas notificações sem precisar interagir diretamente com a lógica de finalização de pedidos.
- Facilidade de Extensão: O sistema está agora preparado para ser estendido com novos tipos de notificações ou eventos sem modificar as classes existentes. Isso facilita a manutenção do código e a adição de novas funcionalidades.
Benefícios da Refatoração
- Desacoplamento Maior: O padrão Observer permitiu que a interface gráfica fosse desacoplada da lógica de negócios. Agora, a interface não precisa mais interagir diretamente com a classe Restaurante, o que facilita futuras alterações ou adições no código sem afetar a interface gráfica.
- Escalabilidade e Manutenção: Com a implementação do Observer, novos componentes podem ser adicionados para "observar" diferentes eventos (como a finalização de pedidos) sem a necessidade de alterar o código existente. Isso torna o sistema mais flexível e fácil de manter.
- Organização do Código: A refatoração resultou em um código mais modular, com a separação clara entre a lógica de negócios e a lógica de exibição. Isso facilita a manutenção e evolução do sistema.
  
# Conclusão
A implementação do padrão Observer trouxe uma melhoria no gerenciamento de pedidos. Ao utilizar esse padrão, conseguimos reduzir o acoplamento entre os componentes, permitindo que a interface gráfica e o sistema de métricas de vendas sejam notificados automaticamente sobre a finalização de pedidos, sem a necessidade de interações diretas entre eles.

# Gerenciamento de Restaurante

Este projeto é uma aplicação Java com interface gráfica para o gerenciamento de um restaurante, facilitando o controle de clientes, pedidos, fila de espera, métricas de vendas e entregas.

## Funcionalidades

* **Adicionar Clientes:** Registrar nome do cliente e quantidade de pessoas para a mesa.
* **Gerenciamento de Pedidos:** Adicionar pedidos, encerrar pedidos e finalizar pagamento.
* **Fila de Espera:** Gerenciar a fila de espera para os clientes que aguardam mesas.
* **Métricas de Venda:** Visualização das métricas de vendas realizadas no restaurante.
* **Delivery:** Entregas com cobrança de taxa adicional para distâncias superiores a 3km.
* **Formas de Pagamento:** Aceita pagamento via Pix, débito, crédito e dinheiro.

## Como Executar

Certifique-se de ter o Java JDK instalado.

1.  Clone o repositório:

    ```bash
    git clone [https://github.com/usuario/projeto-gerenciamento-restaurante.git](https://github.com/usuario/projeto-gerenciamento-restaurante.git)
    ```

2.  Compile e execute o projeto:

    ```bash
    javac Main.java
    java Main
    ```

## Estrutura do Projeto

* **Clientes:** Gerenciamento de clientes e pedidos.
* **Fila de Espera:** Controle de clientes aguardando mesas.
* **Métricas:** Cálculo e exibição das vendas diárias.
* **Delivery:** Controle de entregas e cálculo de taxas.
* **Pagamentos:** Processamento de diferentes formas de pagamento (Pix, débito, crédito, dinheiro).

## Requisitos

* Java 8 ou superior
* Interface gráfica (Java Swing, JavaFX, etc.)