package model;

import java.math.BigDecimal;

public class Servico extends Produto {

    private int duracao;

    public Servico(int id, String nome, Tipo tipo, int duracao, int valor) {
        super();
        this.setIdProduto(id);
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
        return "Servico{" +
                "duracao=" + duracao +
                '}';
    }
}
