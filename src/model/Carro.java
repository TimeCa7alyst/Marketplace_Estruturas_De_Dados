package model;

public class Carro {

    private int idCarro;
    private String nome;
    private MarcaCarro marca;

    public Carro(int idCarro, String nome, MarcaCarro marca) {
        this.idCarro = idCarro;
        this.nome = nome;
        this.marca = marca;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public MarcaCarro getMarca() {
        return marca;
    }

    public void setMarca(MarcaCarro marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "[" + idCarro + "] " +
                "Nome = " + nome + " | " +
                "Marca = " + marca.getNome();
    }
}
