package controller;

import estruturas.ListaSimplesDinamica;
import model.Marca;

public class MarcaController {

    ListaSimplesDinamica<Marca> lista;

    public MarcaController(ListaSimplesDinamica<Marca> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Marca marca) {
        lista.insere(marca);
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
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Marca ma = lista.getDado(i);

            if (ma.getIdMarca() == id) {
                return ma;
            }
        }
        System.out.println("Marca de ID: " + id + " não encontrada");
        return null;
    }

    public Marca findByName(String name) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Marca ma = lista.getDado(i);

            if (ma.getNome().equals(name)) {
                return ma;
            }
        }
        System.out.println("Marca de nome: " + name + " não encontrada");
        return null;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Marca ma = lista.getDado(i);

            if (ma.getIdMarca() == id) {
                lista.removeMeio(i);
                System.out.println("Marca removida com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover a marca de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Marca(1, "Bosch"));
        create(new Marca(2, "Monroe"));
        create(new Marca(3, "Nakata"));
    }

}
