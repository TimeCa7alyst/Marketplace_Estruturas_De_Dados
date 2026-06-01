package model;

import java.math.BigDecimal;

public class Peca extends Produto {

    private Marca marca;

    private Carro modeloCarro;

    public Peca(int id, String nome, Marca marca, int valor, Carro modeloCarro) {
        super();
        this.setIdProduto(id);
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
        return "Peca{" +
                "marca=" + marca +
                ", modeloCarro=" + modeloCarro +
                '}';
    }
}
