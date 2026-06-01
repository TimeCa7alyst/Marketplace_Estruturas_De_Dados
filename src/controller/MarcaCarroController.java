package controller;

import estruturas.ListaSimplesDinamica;
import model.MarcaCarro;

public class MarcaCarroController {

    ListaSimplesDinamica<MarcaCarro> lista;

    public MarcaCarroController(ListaSimplesDinamica<MarcaCarro> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(MarcaCarro marca) {
        lista.insere(marca);
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
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            MarcaCarro m = lista.getDado(i);

            if (m.getIdMarcaCarro() == id) {
                return m;
            }
        }
        System.out.println("Marca de ID: " + id + " não encontrada");
        return null;
    }

    public MarcaCarro findByName(String name) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            MarcaCarro m = lista.getDado(i);

            if (m.getNome().equals(name)) {
                return m;
            }
        }
        System.out.println("Marca de nome: " + name + " não encontrada");
        return null;
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
        create(new MarcaCarro(1, "Fiat"));
        create(new MarcaCarro(2, "BMW"));
        create(new MarcaCarro(3, "Ford"));
        create(new MarcaCarro(4, "Toyota"));
        create(new MarcaCarro(5, "Citroen"));
    }
}


