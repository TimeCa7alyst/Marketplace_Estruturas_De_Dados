package controller;

import services.ArvoreBinaria;
import services.ListaSimplesDinamica;
import model.Tipo;

import java.util.Comparator;

public class TipoController {

    ListaSimplesDinamica<Tipo> lista;

    ArvoreBinaria<Tipo> arvoreNome = new ArvoreBinaria<>(new Comparator<Tipo>() {
        @Override
        public int compare(Tipo o1, Tipo o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    });

    public TipoController(ListaSimplesDinamica<Tipo> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Tipo tipo) {
        lista.insere(tipo);
        arvoreNome.insere(tipo);
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

        Tipo tipoObj = new Tipo(name);

        Tipo tipoResponse = arvoreNome.busca(tipoObj);

        if (tipoResponse == null) {
            System.out.println("Tipo de nome: " + name + " não encontrado");
            return null;
        }

        return tipoResponse;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Tipo t = lista.getDado(i);

            if (t.getIdTipo() == id) {
                arvoreNome.remove(t);
                lista.removeMeio(i);
                System.out.println("Tipo removido com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover o tipo de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Tipo("Motor"));
        create(new Tipo("Motores"));
        create(new Tipo("Suspensao"));
        create(new Tipo("Freios"));
        create(new Tipo("Alimentacao"));
        create(new Tipo("Eletrica"));
        create(new Tipo("Ignicao"));
        create(new Tipo("Rodas"));
        create(new Tipo("Instalacao"));
        create(new Tipo("Manutencao"));
        create(new Tipo("Limpeza"));
        create(new Tipo("Revisao"));
    }
}
