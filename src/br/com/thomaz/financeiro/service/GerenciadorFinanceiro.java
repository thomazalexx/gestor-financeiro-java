package br.com.thomaz.financeiro.service;

import br.com.thomaz.financeiro.model.Categoria;
import br.com.thomaz.financeiro.model.TipoTransacao;
import br.com.thomaz.financeiro.model.Transacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GerenciadorFinanceiro {
    private List<Transacao> transacoes;

    public GerenciadorFinanceiro() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(String descricao, double valor, TipoTransacao tipo, Categoria categoria) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Transacao novaTransacao = new Transacao(id, descricao, valor, tipo, categoria, LocalDate.now());
        transacoes.add(novaTransacao);
        System.out.println("Transação adicionada com sucesso!");
    }

    public List<Transacao> listarTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public double calcularSaldoTotal() {
        double totalReceitas = 0;
        double totalDespesas = 0;

        for (Transacao t : transacoes) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                totalReceitas += t.getValor();
            } else if (t.getTipo() == TipoTransacao.DESPESA) {
                totalDespesas += t.getValor();
            }
        }

        return totalReceitas - totalDespesas;
    }
}