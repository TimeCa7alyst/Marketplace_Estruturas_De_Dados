package controller;

import estruturas.ListaSimplesDinamica;
import model.Carro;
import model.Marca;
import model.MarcaCarro;
import model.Peca;

public class PecaController {

    ListaSimplesDinamica<Peca> lista;

    public PecaController(ListaSimplesDinamica<Peca> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Peca peca) {
        lista.insere(peca);
        System.out.println("Peça cadastrada com sucesso");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista vazia | Nenhuma peça cadastrada");
        } else {
            System.out.println("Lista de peças:");
            lista.printLista();
        }
    }

    public Peca findById(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getIdProduto() == id) {
                return p;
            }
        }
        System.out.println("Peça de ID: " + id + " não encontrada");
        return null;
    }

    public Peca findByMarca(String marca) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getMarca().getNome().equals(marca)) {
                return p;
            }
        }
        return null;
    }

    public Peca findByTipo(String tipo) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getTipo().toString().equals(tipo)) {
                return p;
            }
        }
        return null;
    }

    public Peca findByName(String nome) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        System.out.println("Peça de nome: " + nome + " não encontrada");
        return null;
    }

    public Peca findByCarro(String carro) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getModeloCarro().getNome().equals(carro)) {
                return p;
            }
        }
        return null;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getIdProduto() == id) {
                lista.removeMeio(i);
                System.out.println("Peça removida com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover a peça de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Peca(new Marca(1, "Bosch"),
                new Carro(1, "Santana", new MarcaCarro(1, "Volkswagen"))));
    }

}
