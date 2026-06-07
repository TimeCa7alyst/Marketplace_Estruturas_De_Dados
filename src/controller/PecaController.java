package controller;

import services.ArvoreBinaria;
import services.ListaSimplesDinamica;
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
        System.out.println("Peça cadastrada com sucesso nas árvores");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista vazia | Nenhuma peça cadastrada");
        } else {
            System.out.println("Lista de peças:\n");
            lista.printLista();
        }
    }


    public Peca findById(int id) {
        Peca pecaObj = new Peca(id);
        return arvoreId.busca(pecaObj);
    }

    public Peca findByMarca(String marca) {
        Peca pecaObj = new Peca(null, new Marca(marca), 0, new Carro(null, new MarcaCarro(null)));
        return arvoreMarca.busca(pecaObj);
    }

    public Peca findByTipo(String tipo) {
        Peca pecaObj = new Peca(null, null, 0, new Carro(null, new MarcaCarro(null)));
        pecaObj.setTipo(new Tipo(tipo));
        return arvoreTipo.busca(pecaObj);
    }

    public Peca findByName(String nome) {
        Peca pecaObj = new Peca(nome, null, 0, new Carro(null, new MarcaCarro(null)));
        return arvoreNome.busca(pecaObj);
    }

    public Peca findByCarro(String carro) {
        Peca pecaObj = new Peca(null, null, 0, new Carro(carro, new MarcaCarro(null)));
        return arvoreCarro.busca(pecaObj);
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
        Peca peca1 = new Peca("Amortecedor", new Marca("Bosch"), 350, new Carro("Gol", new MarcaCarro("Volkswagen")));
        peca1.setTipo(new Tipo("Suspensao"));
        create(peca1);

        Peca peca2 = new Peca("Pastilha de Freio", new Marca("Nakata"), 120, new Carro("Uno", new MarcaCarro("Fiat")));
        peca2.setTipo(new Tipo("Freios"));
        create(peca2);

        Peca peca3 = new Peca("Carburador Brosol 2E", new Marca("Brosol"), 850, new Carro("Santana", new MarcaCarro("Volkswagen")));
        peca3.setTipo(new Tipo("Alimentacao"));
        create(peca3);

        Peca peca4 = new Peca("Bateria 60Ah", new Marca("Moura"), 450, new Carro("Corolla", new MarcaCarro("Toyota")));
        peca4.setTipo(new Tipo("Eletrica"));
        create(peca4);

        Peca peca5 = new Peca("Filtro de Oleo", new Marca("Bosch"), 45, new Carro("Civic", new MarcaCarro("Honda")));
        peca5.setTipo(new Tipo("Motor"));
        create(peca5);

        Peca peca6 = new Peca("Velas de Ignicao", new Marca("NGK"), 160, new Carro("Celta", new MarcaCarro("Chevrolet")));
        peca6.setTipo(new Tipo("Ignicao"));
        create(peca6);

        Peca peca7 = new Peca("Pneu 175/70 R14", new Marca("Pirelli"), 380, new Carro("Palio", new MarcaCarro("Fiat")));
        peca7.setTipo(new Tipo("Rodas"));
        create(peca7);
    }
}