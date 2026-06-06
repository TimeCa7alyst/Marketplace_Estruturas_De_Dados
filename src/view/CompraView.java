package view;

import controller.CarroController;
import controller.CompraController;
import controller.PecaController;
import controller.ServicoController;
import model.*;

import java.util.Scanner;

public class CompraView {
    private CompraController compraCtrl;
    private PecaController pecaCtrl;
    private ServicoController servicoCtrl;
    private Scanner scanner;

    public CompraView(CompraController compraCtrl, PecaController pecaCtrl, ServicoController servicoCtrl) {
        this.compraCtrl = compraCtrl;
        this.pecaCtrl = pecaCtrl;
        this.servicoCtrl = servicoCtrl;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n=== MÓDULO DE COMPRA ===");
            System.out.println("1 - Realizar Compra");
            System.out.println("2 - Listar todas as Compras");
            System.out.println("3 - Buscar Compra (por ID)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarCompra();
                        break;
                    case 2:
                        listarTodos();
                        break;
                    case 3:
                        buscarPorId();
                        break;
                    case 0:
                        System.out.println("Encerrando módulo de compras...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void cadastrarCompra() {
        System.out.println("\n--- Realizar Compra ---");

        Compra novaCompra = new Compra();
        boolean adicionandoItens = true;

        while (adicionandoItens) {
            System.out.print("\nDigite o ID ou Nome da peça ou serviço (ou '0' para finalizar): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equals("0")) {
                adicionandoItens = false;
                continue;
            }

            boolean itemEncontrado = false;

            try {
                int id = Integer.parseInt(entrada);

                Servico servico = servicoCtrl.findById(id);
                if (servico != null) {
                    novaCompra.getServicos().insere(servico);
                    novaCompra.setTotal(servico.getPrecoBase());
                    System.out.println("Serviço adicionado! Subtotal: R$ " + novaCompra.getTotal());
                    itemEncontrado = true;
                } else {
                    Peca peca = pecaCtrl.findById(id);
                    if (peca != null) {
                        novaCompra.getPecas().insere(peca);
                        novaCompra.setTotal(peca.getPrecoBase());
                        System.out.println("Peça adicionada! Subtotal: R$ " + novaCompra.getTotal());
                        itemEncontrado = true;
                    }
                }

            } catch (NumberFormatException e) {
                Peca peca = pecaCtrl.findByName(entrada);
                if (peca != null) {
                    novaCompra.getPecas().insere(peca);
                    novaCompra.setTotal(peca.getPrecoBase());
                    System.out.println("Peça adicionada! Subtotal: R$ " + novaCompra.getTotal());
                    itemEncontrado = true;
                }
            }

            if (!itemEncontrado) {
                System.out.println("Nenhum item (Peça ou Serviço) encontrado com esse identificador. Tente novamente.");
            }
        }

        if (novaCompra.getPecas().listaVazia() && novaCompra.getServicos().listaVazia()) {
            System.out.println("Operação cancelada.");
        } else {
            compraCtrl.create(novaCompra);
            System.out.println("\nCompra registrada com sucesso!");
            System.out.println("Valor Final da Compra: R$ " + novaCompra.getTotal());
        }
    }

    private void buscarPorId() {
        System.out.println("\n--- Buscar Compra por ID ---");
        System.out.print("Digite o ID da compra: ");
        int id = Integer.parseInt(scanner.nextLine());

        Compra compra = compraCtrl.findById(id);
        System.out.println(compra.toString());
    }

    private void listarTodos() {
        System.out.println("\n--- Registro de Compras ---");
        compraCtrl.findAll();
    }
}
