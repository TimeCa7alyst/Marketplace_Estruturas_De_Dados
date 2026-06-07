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

    ArvoreBinaria<Servico> arvoreNome = new ArvoreBinaria<>(new Comparator<Servico>() {
        @Override
        public int compare(Servico o1, Servico o2) {
            return o1.getNome().compareToIgnoreCase(o2.getNome());
        }
    });

    public ServicoController(ListaSimplesDinamica<Servico> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Servico servico) {
        lista.insere(servico);
        arvoreNome.insere(servico);
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
        Servico servicoObj = new Servico();
        servicoObj.setIdProduto(id);

        return arvoreId.busca(servicoObj);
    }

    public Servico findByName(String nome) {
        Servico servicoObj = new Servico();
        servicoObj.setNome(nome);

        return arvoreNome.busca(servicoObj);
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Servico s = lista.getDado(i);

            if (s.getIdProduto() == id) {
                arvoreId.remove(s);
                arvoreNome.remove(s);
                lista.removeMeio(i);
                System.out.println("Serviço removido com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover o serviço de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Servico("Instalacao motor", new Tipo("Instalacao"), 3, 3000));
        create(new Servico("Troca de oleo", new Tipo("Manutencao"), 1, 150));
        create(new Servico("Alinhamento e Balanceamento", new Tipo("Manutencao"), 2, 200));
        create(new Servico("Manutencao Carburador", new Tipo("Alimentacao"), 4, 350));
        create(new Servico("Limpeza de Bico Injetor", new Tipo("Limpeza"), 1, 180));
        create(new Servico("Revisao Eletrica Motor", new Tipo("Revisao"), 3, 280));
        create(new Servico("Troca de Pastilhas de Freio", new Tipo("Manutencao"), 2, 250));
        create(new Servico("Troca de Amortecedores", new Tipo("Suspensao"), 3, 600));
    }
}
