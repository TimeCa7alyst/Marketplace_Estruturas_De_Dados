package model;

public class MarcaCarro {

    private int idMarcaCarro;
    private String nome;

    public MarcaCarro(int idMarcaCarro, String nome) {
        this.idMarcaCarro = idMarcaCarro;
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
        return "MarcaCarro{" +
                "idMarcaCarro=" + idMarcaCarro +
                ", nome='" + nome + '\'' +
                '}';
    }
}
