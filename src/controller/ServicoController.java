package controller;

import services.ArvoreBinaria;
import services.ListaSimplesDinamica;
import model.Servico;
import model.Tipo;

import java.util.Comparator;

public class ServicoController {

    ListaSimplesDinamica<Servico> lista;

    ArvoreBinaria<Servico> arvoreId = new ArvoreBinaria<>(new Comparator<Servico>() {
        @Override
        public int compare(Servico o1, Servico o2) {
            return Integer.compare(o1.getIdProduto(), o2.getIdProduto());
        }
    });

    public ServicoController(ListaSimplesDinamica<Servico> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Servico servico) {
        lista.insere(servico);
        arvoreId.insere(servico);
        System.out.println("Serviço inserido com sucesso");
    }

    public void findAll() {
        if (lista.listaVazia()) {
            System.out.println("Lista vazia | Nenhum serviço cadastrado");
        } else {
            System.out.println("Lista de serviços");
            lista.printLista();
        }
    }

    public Servico findById(int id) {

        Servico servicoObj = new Servico(id);

        Servico servicoResponse = arvoreId.busca(servicoObj);

        if (servicoResponse == null) {
            System.out.println("Serviço com ID: " + id + " não foi encontrado");
            return null;
        }
        return servicoResponse;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Servico s = lista.getDado(i);

            if (s.getIdProduto() == id) {
                arvoreId.remove(s);
                lista.removeMeio(i);
                System.out.println("Serviço removido com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover o serviço de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Servico("Instalação motor", new Tipo("Instalacao"),
                3, 3000));
    }
}
