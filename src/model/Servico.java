package model;

import java.math.BigDecimal;

public class Servico extends Produto {

    private int duracao;

    public Servico(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public BigDecimal calcularPrecoTotal() {
        return null;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "duracao=" + duracao +
                '}';
    }
}
