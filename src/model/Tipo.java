package model;

import java.util.concurrent.ThreadLocalRandom;

public class Tipo {

    private int idTipo;
    private String nome;



    public Tipo(String nome) {
        this.idTipo = ThreadLocalRandom.current().nextInt(1_000_000);
        this.nome = nome;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "[" + idTipo + "] - " + nome;
    }
}
