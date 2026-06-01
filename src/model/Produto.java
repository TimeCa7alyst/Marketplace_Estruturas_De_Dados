package model;

import java.math.BigDecimal;

abstract class Produto {
    private int idProduto;
    private String nome;
    private Tipo tipo;
    private BigDecimal precoBase;


    public abstract BigDecimal calcularPrecoTotal();

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", precoBase=" + precoBase +
                '}';
    }
}
