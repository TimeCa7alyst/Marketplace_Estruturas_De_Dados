package controller;

import estruturas.ArvoreBinaria;
import estruturas.ListaSimplesDinamica;
import model.Carro;
import model.Marca;
import model.MarcaCarro;
import model.Peca;
import model.Tipo;

import java.util.Comparator;

public class PecaController {

    ListaSimplesDinamica<Peca> lista;

    ArvoreBinaria<Peca> arvoreId = new ArvoreBinaria<>(new Comparator<Peca>() {
        @Override
        public int compare(Peca o1, Peca o2) {
            return Integer.compare(o1.getIdProduto(), o2.getIdProduto());
        }
    });

    ArvoreBinaria<Peca> arvoreMarca = new ArvoreBinaria<>(new Comparator<Peca>() {
        @Override
        public int compare(Peca o1, Peca o2) {
            return o1.getMarca().getNome().compareToIgnoreCase(o2.getMarca().getNome());
        }
    });

    ArvoreBinaria<Peca> arvoreTipo = new ArvoreBinaria<>(new Comparator<Peca>() {
        @Override
        public int compare(Peca o1, Peca o2) {
            return o1.getTipo().getNome().compareToIgnoreCase(o2.getTipo().getNome());
        }
    });

    ArvoreBinaria<Peca> arvoreNome = new ArvoreBinaria<>(new Comparator<Peca>() {
        @Override
        public int compare(Peca o1, Peca o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    });

    ArvoreBinaria<Peca> arvoreCarro = new ArvoreBinaria<>(new Comparator<Peca>() {
        @Override
        public int compare(Peca o1, Peca o2) {
            return o1.getModeloCarro().getNome().compareToIgnoreCase(o2.getModeloCarro().getNome());
        }
    });

    public PecaController(ListaSimplesDinamica<Peca> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Peca peca) {
        lista.insere(peca);
        arvoreId.insere(peca);
        arvoreMarca.insere(peca);
        arvoreTipo.insere(peca);
        arvoreNome.insere(peca);
        arvoreCarro.insere(peca);
        System.out.println("Peça cadastrada com sucesso nas árvores de indexação.");
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
        Peca pecaObj = new Peca(id, null, null, 0, new Carro(0, null, new MarcaCarro(0, null)));
        Peca pecaResponse = arvoreId.busca(pecaObj);

        if (pecaResponse == null) {
            System.out.println("Peça de ID: " + id + " não encontrada");
            return null;
        }
        return pecaResponse;
    }

    public Peca findByMarca(String marca) {
        Peca pecaObj = new Peca(0, null, new Marca(0, marca), 0, new Carro(0, null, new MarcaCarro(0, null)));
        Peca pecaResponse = arvoreMarca.busca(pecaObj);

        if (pecaResponse == null) {
            System.out.println("Nenhuma peça da marca: " + marca + " foi encontrada");
            return null;
        }
        return pecaResponse;
    }

    public Peca findByTipo(String tipo) {
        Peca pecaObj = new Peca(0, null, null, 0, new Carro(0, null, new MarcaCarro(0, null)));
        pecaObj.setTipo(new Tipo(0, tipo));

        Peca pecaResponse = arvoreTipo.busca(pecaObj);

        if (pecaResponse == null) {
            System.out.println("Nenhuma peça do tipo: " + tipo + " foi encontrada");
            return null;
        }
        return pecaResponse;
    }

    public Peca findByName(String nome) {
        Peca pecaObj = new Peca(0, nome, null, 0, new Carro(0, null, new MarcaCarro(0, null)));
        Peca pecaResponse = arvoreNome.busca(pecaObj);

        if (pecaResponse == null) {
            System.out.println("Peça de nome: " + nome + " não encontrada");
            return null;
        }
        return pecaResponse;
    }

    public Peca findByCarro(String carro) {
        Peca pecaObj = new Peca(0, null, null, 0, new Carro(0, carro, new MarcaCarro(0, null)));
        Peca pecaResponse = arvoreCarro.busca(pecaObj);

        if (pecaResponse == null) {
            System.out.println("Nenhuma peça para o carro: " + carro + " foi encontrada");
            return null;
        }
        return pecaResponse;
    }


    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Peca p = lista.getDado(i);

            if (p.getIdProduto() == id) {
                arvoreId.remove(p);
                arvoreNome.remove(p);
                arvoreMarca.remove(p);
                arvoreTipo.remove(p);
                arvoreCarro.remove(p);
                lista.removeMeio(i);
                System.out.println("Peça removida com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover a peça de ID: " + id);
        return false;
    }

    private void dadosMock() {
        Peca pecaMock = new Peca(1, "Amortecedor", new Marca(1, "Bosch"), 3000, new Carro(1, "Gol", new MarcaCarro(1, "Volkswagen")));

        pecaMock.setTipo(new Tipo(1, "Suspensão"));

        create(pecaMock);
    }
}