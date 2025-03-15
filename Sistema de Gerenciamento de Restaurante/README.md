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