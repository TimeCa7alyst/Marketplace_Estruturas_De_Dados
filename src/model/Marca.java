package model;

import java.util.concurrent.ThreadLocalRandom;

public class Marca {

    private int idMarca;
    private String nome;

    public Marca() {
    }

    public Marca(int idMarca) {
        this.idMarca = idMarca;
    }

    public Marca(String nome) {
        this.idMarca = ThreadLocalRandom.current().nextInt(1_000_000);
        this.nome = nome;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "[" + idMarca + "] " +
                "Nome = " + nome + " | ";
    }
}
