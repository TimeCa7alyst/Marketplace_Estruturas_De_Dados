package controller;

import estruturas.ListaSimplesDinamica;
import model.Servico;

public class ServicoController {

    ListaSimplesDinamica<Servico> lista;

    public ServicoController(ListaSimplesDinamica<Servico> lista) {
        this.lista = lista;
        dadosMock();
    }

    public void create(Servico servico) {
        lista.insere(servico);
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
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Servico s = lista.getDado(i);

            if (s.getIdProduto() == id) {
                return s;
            }
        }
        System.out.println("Serviço com ID: " + id + " não foi encontrado");
        return null;
    }

    public boolean remove(int id) {
        for (int i = 0; i < lista.tamanhoLista(); i++) {
            Servico s = lista.getDado(i);

            if (s.getIdProduto() == id) {
                lista.removeMeio(i);
                System.out.println("Serviço removido com sucesso");
                return true;
            }
        }
        System.out.println("Não foi possível remover o serviço de ID: " + id);
        return false;
    }

    private void dadosMock() {
        create(new Servico(3));
    }
}
