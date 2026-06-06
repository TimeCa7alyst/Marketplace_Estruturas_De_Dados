package model;

import java.util.concurrent.ThreadLocalRandom;

public class MarcaCarro {

    private int idMarcaCarro;
    private String nome;

    public MarcaCarro() {
    }

    public MarcaCarro(int idMarcaCarro) {
        this.idMarcaCarro = idMarcaCarro;
    }

    public MarcaCarro(String nome) {
        this.idMarcaCarro = ThreadLocalRandom.current().nextInt(1_000_000);
        this.nome = nome;
    }

    public int getIdMarcaCarro() {
        return idMarcaCarro;
    }

    public void setIdMarcaCarro(int idMarcaCarro) {
        this.idMarcaCarro = idMarcaCarro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "[" + idMarcaCarro + "] " +
                "Nome = " + nome;

    }
}
