package model;

import java.math.BigDecimal;

abstract class Produto {
    private int idProduto;
    private String nome;
    private Tipo tipo;
    private int precoBase;


    public abstract int calcularPrecoTotal();

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

    public int getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(int precoBase) {
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
