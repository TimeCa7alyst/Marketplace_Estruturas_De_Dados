package model;

public class Tipo {

    private int idTipo;
    private String nome;

    public Tipo(int idTipo, String nome) {
        this.idTipo = idTipo;
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
        return "Tipo{" +
                "idTipo=" + idTipo +
                ", nome='" + nome + '\'' +
                '}';
    }
}
