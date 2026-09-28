package br.com.thomaz.financeiro.service;

import br.com.thomaz.financeiro.model.Categoria;
import br.com.thomaz.financeiro.model.TipoTransacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorExtratoCSV {

    public static void importarCSV(String caminhoArquivo, GerenciadorFinanceiro gerenciador) {
        String linha;
        String divisor = ",";
        int importados = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            // Pula o cabeçalho (data,descricao,valor,categoria)
            br.readLine();

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(divisor);

                if (dados.length >= 4) {
                    String descricao = dados[1].trim();
                    double valor = Double.parseDouble(dados[2].trim());
                    String categoriaTexto = dados[3].trim().toUpperCase();

                    // Identifica se é Receita ou Despesa pelo sinal do valor
                    TipoTransacao tipo = (valor >= 0) ? TipoTransacao.RECEITA : TipoTransacao.DESPESA;
                    double valorAbsoluto = Math.abs(valor);

                    // Converte texto da categoria para o Enum Categoria
                    Categoria categoria;
                    try {
                        categoria = Categoria.valueOf(categoriaTexto);
                    } catch (IllegalArgumentException e) {
                        categoria = Categoria.OUTROS;
                    }

                    gerenciador.adicionarTransacao(descricao, valorAbsoluto, tipo, categoria);
                    importados++;
                }
            }
            System.out.println("\n Importação concluída! " + importados + " transações importadas com sucesso.");

        } catch (IOException e) {
            System.out.println("\n Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n Erro de formatação de número no arquivo CSV: " + e.getMessage());
        }
    }
}