package controller;

import estruturas.ListaSimplesDinamica;
import model.Carro;
import model.MarcaCarro;

public class CarroController {

    ListaSimplesDinamica<Carro> lista;

    public CarroController(ListaSimplesDinamica<Carro> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Carro carro) {
        lista.insere(carro);
        System.out.println("Carro cadastrado com sucesso");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista vazia | Nenhum carro cadastrado");
        } else {
            System.out.println("Lista de carros:");
            lista.printLista();
        }
    }

    public Carro findById(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Carro c = lista.getDado(i);

            if (c.getIdCarro() == id) {
                return c;
            }
        }
        System.out.println("Carro com ID: " + id + " não foi encontrado");
        return null;
    }

    public Carro findByName(String name) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Carro c = lista.getDado(i);

            if (c.getNome().equals(name)) {
                return c;
            }
        }
        System.out.println("Carro de nome: " + name + " não encontrado");
        return null;
    }

    public Carro findByMarca(String marca) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Carro c = lista.getDado(i);

            if (c.getMarca().getNome().equals(marca)) {
                return c;
            }
        }
        return null;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Carro c = lista.getDado(i);

            if (c.getIdCarro() == id) {
                lista.removeMeio(i);
                System.out.println("Carro removido com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover o carro de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Carro(1, "Uno",
                new MarcaCarro(1, "Fiat")));
        create(new Carro(2, "Gol",
                new MarcaCarro(2, "Volkswagen")));
    }
}
