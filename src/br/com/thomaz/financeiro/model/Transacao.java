package br.com.thomaz.financeiro.model;

import java.time.LocalDate;

public class Transacao {
    private String id;
    private String descricao;
    private double valor;
    private TipoTransacao tipo;
    private Categoria categoria;
    private LocalDate data;

    public Transacao(String id, String descricao, double valor, TipoTransacao tipo, Categoria categoria, LocalDate data){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.data = data;
    }
    public String getId() { return id;}
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public TipoTransacao getTipo() { return tipo; }
    public Categoria getCategoria() { return categoria; }
    public LocalDate getData() { return data; }

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | %s - R$ %.2f (%s) - %s",
                data, id, tipo, valor, categoria, descricao);
    }
}
