package controller;

import services.ArvoreBinaria;
import services.ListaSimplesDinamica;
import model.Marca;

import java.util.Comparator;

public class MarcaController {

    ListaSimplesDinamica<Marca> lista;

    ArvoreBinaria<Marca> arvoreId = new ArvoreBinaria<>(new Comparator<Marca>() {
        @Override
        public int compare(Marca o1, Marca o2) {
            return Integer.compare(o1.getIdMarca(), o2.getIdMarca());
        }
    });

    ArvoreBinaria<Marca> arvoreNome = new ArvoreBinaria<>(new Comparator<Marca>() {
        @Override
        public int compare(Marca o1, Marca o2) {
            return o1.getNome().compareTo(o2.getNome());
        }
    });

    public MarcaController(ListaSimplesDinamica<Marca> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Marca marca) {
        lista.insere(marca);
        arvoreId.insere(marca);
        arvoreNome.insere(marca);
        System.out.println("Marca cadastrada com sucesso");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista Vazia | Nenhuma marca cadastrada");
        } else {
            System.out.println("Lista de marcas:");
            lista.printLista();
        }
    }

    public Marca findById(int id) {

        Marca marcaObj = new Marca(id);

        Marca marcaResponse = arvoreId.busca(marcaObj);

        if (marcaResponse == null) {
            System.out.println("Marca de ID: " + id + " não encontrada");
            return null;
        }

        return marcaResponse;
    }

    public Marca findByName(String name) {

        Marca marcaObj = new Marca(name);

        Marca marcaResponse = arvoreNome.busca(marcaObj);

        if (marcaResponse == null) {
            System.out.println("Marca de nome: " + name + " não encontrada");
            return null;
        }

        return marcaResponse;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Marca ma = lista.getDado(i);

            if (ma.getIdMarca() == id) {
                arvoreId.remove(ma);
                arvoreNome.remove(ma);
                lista.removeMeio(i);
                System.out.println("Marca removida com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover a marca de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Marca("Bosch"));
        create(new Marca("Monroe"));
        create(new Marca("Nakata"));
    }

}
