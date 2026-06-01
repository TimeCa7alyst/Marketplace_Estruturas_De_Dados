package controller;

import estruturas.ArvoreBinaria;
import estruturas.ListaSimplesDinamica;
import model.Carro;
import model.MarcaCarro;

import java.util.Comparator;

public class CarroController {

    ListaSimplesDinamica<Carro> lista;

    ArvoreBinaria<Carro> arvoreId = new ArvoreBinaria<>(new Comparator<Carro>() {
        @Override
        public int compare(Carro o1, Carro o2) {
            return Integer.compare(o1.getIdCarro(), o2.getIdCarro());
        }
    });

    ArvoreBinaria<Carro> arvoreNome = new ArvoreBinaria<>(new Comparator<Carro>() {
        @Override
        public int compare(Carro o1, Carro o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    });

    ArvoreBinaria<Carro> arvoreMarca = new ArvoreBinaria<>(new Comparator<Carro>() {
        @Override
        public int compare(Carro o1, Carro o2) {
            return o1.getMarca().getNome().compareToIgnoreCase(o2.getMarca().getNome());
        }
    });

    public CarroController(ListaSimplesDinamica<Carro> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Carro carro) {
        lista.insere(carro);
        arvoreNome.insere(carro);
        arvoreMarca.insere(carro);
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

        Carro carroObj = new Carro(id, null, null);

        Carro carroResponse = arvoreId.busca(carroObj);

        if (carroResponse == null) {
            System.out.println("Carro de ID: " + id + " não encontrado");
        }
        return carroResponse;
    }

    public Carro findByName(String name) {

        Carro carroObj = new Carro(0, name, null);

        Carro carroResponse = arvoreNome.busca(carroObj);

        if (carroResponse == null) {
            System.out.println("Carro de nome: " + name + " não encontrado");
            return null;
        }

        return carroResponse;
    }

    public Carro findByMarca(String marca) {

        Carro carroObj = new Carro(0, null, new MarcaCarro(0, marca));

        Carro carroResponse = arvoreMarca.busca(carroObj);

        if (carroResponse == null) {
            return null;
        }
        return carroResponse;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Carro c = lista.getDado(i);

            if (c.getIdCarro() == id) {
                arvoreId.remove(c);
                arvoreNome.remove(c);
                arvoreMarca.remove(c);
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
