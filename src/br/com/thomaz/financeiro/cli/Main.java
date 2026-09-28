package br.com.thomaz.financeiro.cli;

import br.com.thomaz.financeiro.model.Categoria;
import br.com.thomaz.financeiro.model.TipoTransacao;
import br.com.thomaz.financeiro.model.Transacao;
import br.com.thomaz.financeiro.service.GerenciadorFinanceiro;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorFinanceiro gerenciador = new GerenciadorFinanceiro();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        System.out.println("=========================================");
        System.out.println("   GESTOR FINANCEIRO PESSOAL - JAVA   ");
        System.out.println("=========================================");

        while (executando) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Adicionar Transação");
            System.out.println("2. Listar Transações");
            System.out.println("3. Ver Saldo Total");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Valor (ex: 150,50 ou 150.50): ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Tipo: 1 - RECEITA | 2 - DESPESA");
                    int tipoOpcao = scanner.nextInt();
                    scanner.nextLine();
                    TipoTransacao tipo = (tipoOpcao == 1) ? TipoTransacao.RECEITA : TipoTransacao.DESPESA;

                    System.out.println("Categorias:");
                    Categoria[] categorias = Categoria.values();
                    for (int i = 0; i < categorias.length; i++) {
                        System.out.println((i + 1) + ". " + categorias[i]);
                    }
                    System.out.print("Escolha a categoria: ");
                    int catOpcao = scanner.nextInt();
                    scanner.nextLine();
                    Categoria categoria = categorias[catOpcao - 1];

                    gerenciador.adicionarTransacao(descricao, valor, tipo, categoria);
                    break;

                case 2:
                    List<Transacao> lista = gerenciador.listarTransacoes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma transação registrada.");
                    } else {
                        System.out.println("\n--- HISTÓRICO DE TRANSAÇÕES ---");
                        for (Transacao t : lista) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 3:
                    double saldo = gerenciador.calcularSaldoTotal();
                    System.out.printf("%nSaldo Atual: R$ %.2f%n", saldo);
                    break;

                case 0:
                    executando = false;
                    System.out.println("Encerrando o sistema... Até mais!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}