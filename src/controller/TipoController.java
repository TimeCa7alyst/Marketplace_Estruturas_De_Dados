package controller;

import estruturas.ListaSimplesDinamica;
import model.Tipo;

public class TipoController {

    ListaSimplesDinamica<Tipo> lista;

    public TipoController(ListaSimplesDinamica<Tipo> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Tipo tipo) {
        lista.insere(tipo);
        System.out.println("Tipo cadastrado com sucesso");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista vazia | Nenhum tipo cadastrado");
        } else {
            System.out.println("Lista de tipos");
            lista.printLista();
        }
    }

    public Tipo findByName(String name) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Tipo t = lista.getDado(i);

            if (t.getNome().equals(name)) {
                return t;
            }
        }
        System.out.println("Tipo de nome: " + name + " não encontrado");
        return null;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Tipo t = lista.getDado(i);

            if (t.getIdTipo() == id) {
                lista.removeMeio(i);
                System.out.println("Tipo removido com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover o tipo de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Tipo(1, "Motores"));
    }
}
