package model;

import services.ListaSimplesDinamica;

import java.util.concurrent.ThreadLocalRandom;

public class Compra {

    private int idCompra;
    private ListaSimplesDinamica<Servico> servicos;
    private ListaSimplesDinamica<Peca> pecas;
    private int total;

    public Compra() {
        this.servicos = new ListaSimplesDinamica<>();
        this.pecas = new ListaSimplesDinamica<>();
        this.idCompra = ThreadLocalRandom.current().nextInt(1_000_000);
        this.total = 0;
    }

    public Compra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Compra(ListaSimplesDinamica<Servico> servicos, ListaSimplesDinamica<Peca> pecas) {
        this.servicos = servicos;
        this.pecas = pecas;
        this.idCompra = ThreadLocalRandom.current().nextInt(1_000_000);
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public ListaSimplesDinamica<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ListaSimplesDinamica<Servico> servicos) {
        this.servicos = servicos;
    }

    public ListaSimplesDinamica<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(ListaSimplesDinamica<Peca> pecas) {
        this.pecas = pecas;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int valor) {
        total += valor;
    }

    @Override
    public String toString() {
        return "[ID da Compra: " + idCompra + "] " +
                "Total = R$" + total  +
                "\nServiços = " + servicos.toString() +
                "\nPeças = " + pecas.toString() + "\n\n" +
                "--------------------------------------\n";
    }
}
