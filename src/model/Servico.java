package model;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Servico extends Produto {

    private int duracao;

    public Servico() {
    }

    public Servico(int duracao) {
        this.duracao = duracao;
    }

    public Servico(String nome, Tipo tipo, int duracao, int valor) {
        super();
        this.setIdProduto(ThreadLocalRandom.current().nextInt(1_000_000));
        this.setNome(nome);
        this.setTipo(tipo);
        this.setPrecoBase(valor);
        this.duracao = duracao;
    }

    @Override
    public int calcularPrecoTotal() {
        return 0;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "[" + getIdProduto() + "] " +
                "Nome = " + getNome() + " | " +
                "Tipo = " + getTipo() + " | " +
                "Valor = " + getPrecoBase() + " | " +
                "Duração do Servico = " + duracao;
    }
}
