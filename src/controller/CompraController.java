package controller;

import model.*;
import services.ArvoreBinaria;
import services.ListaSimplesDinamica;

import java.util.Comparator;

public class CompraController {

    ListaSimplesDinamica<Compra> listaComprasSistema;

    ArvoreBinaria<Compra> arvoreId = new ArvoreBinaria<>(new Comparator<Compra>() {
        @Override
        public int compare(Compra compra, Compra c1) {
            return Integer.compare(compra.getIdCompra(), c1.getIdCompra());
        }
    });

    public CompraController(ListaSimplesDinamica<Compra> listaComprasSistema) {
        this.listaComprasSistema = listaComprasSistema;
        dadosMock();
    }

    public void create(Compra compra) {
        listaComprasSistema.insere(compra);
        arvoreId.insere(compra);
        System.out.println("Compra cadastrada com sucesso!");
    }

    public void findAll() {
        if (listaComprasSistema.listaVazia()) {
            System.out.println("Lista Vazia | Nenhuma compra cadastrada");
        } else {
            System.out.println("Registro de compras:");
            listaComprasSistema.printLista();
        }
    }

    public Compra findById(int id) {

        Compra compraObj = new Compra(id);

        Compra marcaResponse = arvoreId.busca(compraObj);

        if (marcaResponse == null) {
            System.out.println("Compra de ID: " + id + " não encontrada");
            return null;
        }

        return marcaResponse;
    }

    private void dadosMock() {
        ListaSimplesDinamica<Servico> servicos1 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas1 = new ListaSimplesDinamica<>();
        Servico s1 = new Servico("Instalação motor", new Tipo("Instalação"), 3, 3000);
        servicos1.insere(s1);

        Compra c1 = new Compra(servicos1, pecas1);
        c1.setTotal(s1.getPrecoBase());
        create(c1);

        ListaSimplesDinamica<Servico> servicos2 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas2 = new ListaSimplesDinamica<>();
        Peca p1 = new Peca("Amortecedor", new Marca("Bosch"), 350, new Carro("Gol", new MarcaCarro("Volkswagen")));
        p1.setTipo(new Tipo("Suspensão"));
        pecas2.insere(p1);

        Compra c2 = new Compra(servicos2, pecas2);
        c2.setTotal(p1.getPrecoBase());
        create(c2);

        ListaSimplesDinamica<Servico> servicos3 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas3 = new ListaSimplesDinamica<>();
        Servico s2 = new Servico("Troca de óleo", new Tipo("Revisão"), 1, 150);
        Peca p2 = new Peca("Filtro de Óleo", new Marca("ACDelco"), 45, new Carro("Onix", new MarcaCarro("Chevrolet")));
        p2.setTipo(new Tipo("Revisão"));
        servicos3.insere(s2);
        pecas3.insere(p2);

        Compra c3 = new Compra(servicos3, pecas3);
        c3.setTotal(s2.getPrecoBase());
        c3.setTotal(p2.getPrecoBase());
        create(c3);

        ListaSimplesDinamica<Servico> servicos4 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas4 = new ListaSimplesDinamica<>();
        Servico s3 = new Servico("Substituição de bateria", new Tipo("Elétrica"), 1, 50);
        Peca p3 = new Peca("Bateria 60Ah", new Marca("Moura"), 450, new Carro("Civic", new MarcaCarro("Honda")));
        p3.setTipo(new Tipo("Elétrica"));
        servicos4.insere(s3);
        pecas4.insere(p3);

        Compra c4 = new Compra(servicos4, pecas4);
        c4.setTotal(s3.getPrecoBase());
        c4.setTotal(p3.getPrecoBase());
        create(c4);

        ListaSimplesDinamica<Servico> servicos5 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas5 = new ListaSimplesDinamica<>();
        Servico s4 = new Servico("Troca de pastilhas de freio", new Tipo("Freios"), 2, 250);
        Peca p4 = new Peca("Pastilha de Freio", new Marca("TRW"), 120, new Carro("Corolla", new MarcaCarro("Toyota")));
        p4.setTipo(new Tipo("Freios"));
        servicos5.insere(s4);
        pecas5.insere(p4);

        Compra c5 = new Compra(servicos5, pecas5);
        c5.setTotal(s4.getPrecoBase());
        c5.setTotal(p4.getPrecoBase());
        create(c5);

        ListaSimplesDinamica<Servico> servicos6 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas6 = new ListaSimplesDinamica<>();
        Peca p5 = new Peca("Jogo de Velas", new Marca("NGK"), 150, new Carro("HB20", new MarcaCarro("Hyundai")));
        p5.setTipo(new Tipo("Elétrica"));
        Peca p6 = new Peca("Bobina de Ignição", new Marca("Bosch"), 300, new Carro("HB20", new MarcaCarro("Hyundai")));
        p6.setTipo(new Tipo("Elétrica"));
        pecas6.insere(p5);
        pecas6.insere(p6);

        Compra c6 = new Compra(servicos6, pecas6);
        c6.setTotal(p5.getPrecoBase());
        c6.setTotal(p6.getPrecoBase());
        create(c6);

        ListaSimplesDinamica<Servico> servicos7 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas7 = new ListaSimplesDinamica<>();
        Servico s5 = new Servico("Troca de kit de embreagem", new Tipo("Transmissão"), 6, 900);
        Peca p7 = new Peca("Kit Embreagem", new Marca("LUK"), 850, new Carro("Sandero", new MarcaCarro("Renault")));
        p7.setTipo(new Tipo("Transmissão"));
        servicos7.insere(s5);
        pecas7.insere(p7);

        Compra c7 = new Compra(servicos7, pecas7);
        c7.setTotal(s5.getPrecoBase());
        c7.setTotal(p7.getPrecoBase());
        create(c7);

        ListaSimplesDinamica<Servico> servicos8 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas8 = new ListaSimplesDinamica<>();
        Servico s6 = new Servico("Alinhamento", new Tipo("Suspensão"), 1, 80);
        Servico s7 = new Servico("Balanceamento", new Tipo("Suspensão"), 1, 60);
        servicos8.insere(s6);
        servicos8.insere(s7);

        Compra c8 = new Compra(servicos8, pecas8);
        c8.setTotal(s6.getPrecoBase());
        c8.setTotal(s7.getPrecoBase());
        create(c8);

        ListaSimplesDinamica<Servico> servicos9 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas9 = new ListaSimplesDinamica<>();
        Servico s8 = new Servico("Limpeza de bicos", new Tipo("Injeção"), 2, 300);
        Servico s9 = new Servico("Troca de bomba d'água", new Tipo("Refrigeração"), 3, 400);
        Peca p8 = new Peca("Bomba d'água", new Marca("Magneti Marelli"), 220, new Carro("Uno", new MarcaCarro("Fiat")));
        p8.setTipo(new Tipo("Refrigeração"));
        Peca p9 = new Peca("Aditivo Radiador", new Marca("Paraflu"), 80, new Carro("Uno", new MarcaCarro("Fiat")));
        p9.setTipo(new Tipo("Refrigeração"));

        servicos9.insere(s8);
        servicos9.insere(s9);
        pecas9.insere(p8);
        pecas9.insere(p9);

        Compra c9 = new Compra(servicos9, pecas9);
        c9.setTotal(s8.getPrecoBase());
        c9.setTotal(s9.getPrecoBase());
        c9.setTotal(p8.getPrecoBase());
        c9.setTotal(p9.getPrecoBase());
        create(c9);

        ListaSimplesDinamica<Servico> servicos10 = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> pecas10 = new ListaSimplesDinamica<>();
        Peca p10 = new Peca("Farol LED", new Marca("Valeo"), 1500, new Carro("320i", new MarcaCarro("BMW")));
        p10.setTipo(new Tipo("Elétrica"));
        pecas10.insere(p10);

        Compra c10 = new Compra(servicos10, pecas10);
        c10.setTotal(p10.getPrecoBase());
        create(c10);
    }
}
