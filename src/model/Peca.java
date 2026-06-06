package model;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Peca extends Produto {

    private Marca marca;

    private Carro modeloCarro;

    public Peca() {
    }

    public Peca(Integer id) {
        this.setIdProduto(id);
    }

    public Peca(String nome, Marca marca, int valor, Carro modeloCarro) {
        super();
        this.setIdProduto(ThreadLocalRandom.current().nextInt(1_000_000));
        this.setNome(nome);
        this.marca = marca;
        this.modeloCarro = modeloCarro;
        this.setPrecoBase(valor);
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Carro getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(Carro modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    @Override
    public int calcularPrecoTotal() {
        return 0;
    }

    @Override
    public String toString() {
        return "[" + getIdProduto() + "] " +
                "Nome = " + getNome() + " | " +
                "Marca = " + marca.getNome() + " | " +
                "Compatível com = " + modeloCarro.getMarca().getNome() + " " + modeloCarro.getNome() + " | " +
                "Valor = R$" + getPrecoBase();
    }
}
