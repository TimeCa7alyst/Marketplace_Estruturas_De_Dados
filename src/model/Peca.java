package model;

import java.math.BigDecimal;

public class Peca extends Produto {

    private Marca marca;

    private Carro modeloCarro;

    public Peca(Marca marca, Carro modeloCarro) {
        this.marca = marca;
        this.modeloCarro = modeloCarro;
    }

    @Override
    public BigDecimal calcularPrecoTotal() {
        return null;
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
    public String toString() {
        return "Peca{" +
                "marca=" + marca +
                ", modeloCarro=" + modeloCarro +
                '}';
    }
}
