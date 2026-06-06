package controller;

import services.ArvoreBinaria;
import services.ListaSimplesDinamica;
import model.MarcaCarro;

import java.util.Comparator;

public class MarcaCarroController {

    ListaSimplesDinamica<MarcaCarro> lista;

    ArvoreBinaria<MarcaCarro> arvoreId = new ArvoreBinaria<>(new Comparator<MarcaCarro>() {
        @Override
        public int compare(MarcaCarro o1, MarcaCarro o2) {
            return Integer.compare(o1.getIdMarcaCarro(), o2.getIdMarcaCarro());
        }
    });

    ArvoreBinaria<MarcaCarro> arvoreNome = new ArvoreBinaria<>(new Comparator<MarcaCarro>() {

        @Override
        public int compare(MarcaCarro o1, MarcaCarro o2) {
            return o1.getNome().compareTo(o2.getNome());
        }
    });

    public MarcaCarroController(ListaSimplesDinamica<MarcaCarro> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(MarcaCarro marca) {
        lista.insere(marca);
        arvoreId.insere(marca);
        arvoreNome.insere(marca);
        System.out.println("Marca Cadastrada com sucesso");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista Vazia | Nenhuma marca cadastrada");
        } else {
            System.out.println("Lista de marcas:");
            lista.printLista();
        }
    }

    public MarcaCarro findById(int id) {

        MarcaCarro marcaObj = new MarcaCarro(id);

        MarcaCarro marcaResponse = arvoreId.busca(marcaObj);

        if (marcaResponse == null) {
            System.out.println("Marca de ID: " + id + " não encontrada");
            return null;
        }

        return marcaResponse;
    }

    public MarcaCarro findByName(String name) {

        MarcaCarro marcaObj = new MarcaCarro(name);

        MarcaCarro marcaResponse = arvoreNome.busca(marcaObj);

        if (marcaResponse == null) {
            System.out.println("Marca de nome: " + name + " não encontrada");
            return null;
        }

        return marcaResponse;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            MarcaCarro m = lista.getDado(i);

            if (m.getIdMarcaCarro() == id) {
                lista.removeMeio(i);
                System.out.println("Marca Removida com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover a marca de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new MarcaCarro("Fiat"));
        create(new MarcaCarro("BMW"));
        create(new MarcaCarro("Ford"));
        create(new MarcaCarro("Toyota"));
        create(new MarcaCarro("Citroen"));
    }
}


