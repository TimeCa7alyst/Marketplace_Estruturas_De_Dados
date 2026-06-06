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
            System.out.println("4 - Editar Compra");
            System.out.println("5 - Excluir Compra (por ID)");
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
                    case 4:
                        editarCompra();
                        break;
                    case 5:
                        excluirCompra();
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

    private void excluirCompra() {
        System.out.println("\n--- Excluir Compra ---");
        System.out.print("Digite o ID da compra que deseja excluir: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            compraCtrl.remove(id);

        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor, digite um ID numérico válido.");
        }
    }

    private void editarCompra() {
        System.out.println("\n--- Editar Compra ---");
        System.out.print("Digite o ID da compra que deseja editar: ");

        int idCompra;
        try {
            idCompra = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido.");
            return;
        }

        Compra compra = compraCtrl.findById(idCompra);

        if (compra == null) {
            return;
        }

        boolean editando = true;

        while (editando) {
            System.out.println("\n=== Editando Compra [" + compra.getIdCompra() + "] ===");
            System.out.println("Total Atual: R$ " + compra.getTotal());
            System.out.println("1 - Adicionar Item (ID ou Nome)");
            System.out.println("2 - Remover Item (ID ou Nome)");
            System.out.println("0 - Concluir Edição");
            System.out.print("Escolha uma opção: ");

            String opcaoEdit = scanner.nextLine();

            switch (opcaoEdit) {
                case "1":
                    adicionarItemNaEdicao(compra);
                    break;
                case "2":
                    removerItemNaEdicao(compra);
                    break;
                case "0":
                    editando = false;
                    System.out.println("Edição concluída com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (compra.getPecas().listaVazia() && compra.getServicos().listaVazia()) {
                System.out.println("\n⚠️ A compra ficou sem nenhum item (peças ou serviços).");
                System.out.println("Excluindo a compra automaticamente do sistema...");
                compraCtrl.remove(compra.getIdCompra());
                editando = false;
            }
        }
    }

    private void adicionarItemNaEdicao(Compra compra) {
        System.out.print("Digite o ID ou Nome do serviço ou peça para ADICIONAR: ");
        String entrada = scanner.nextLine().trim();
        boolean itemEncontrado = false;

        try {
            int id = Integer.parseInt(entrada);

            Servico servico = servicoCtrl.findById(id);
            if (servico != null) {
                compra.getServicos().insere(servico);
                compra.setTotal(servico.getPrecoBase());
                System.out.println("Serviço adicionado!");
                itemEncontrado = true;
            } else {
                Peca peca = pecaCtrl.findById(id);
                if (peca != null) {
                    compra.getPecas().insere(peca);
                    compra.setTotal(peca.getPrecoBase());
                    System.out.println("Peça adicionada!");
                    itemEncontrado = true;
                }
            }
        } catch (NumberFormatException e) {
            Peca peca = pecaCtrl.findByName(entrada);
            if (peca != null) {
                compra.getPecas().insere(peca);
                compra.setTotal(peca.getPrecoBase());
                System.out.println("Peça adicionada!");
                itemEncontrado = true;
            }
        }

        if (!itemEncontrado) {
            System.out.println("Nenhum item encontrado no estoque com esse identificador.");
        }
    }

    private void removerItemNaEdicao(Compra compra) {
        System.out.print("Digite o ID ou Nome do item presente na compra para REMOVER: ");
        String entrada = scanner.nextLine().trim();
        boolean removido = false;

        try {
            int idBusca = Integer.parseInt(entrada);

            for (int i = 0; i < compra.getServicos().tamanhoLista(); i++) {
                Servico s = compra.getServicos().getDado(i);
                if (s.getIdProduto() == idBusca) {
                    compra.getServicos().removeMeio(i);
                    compra.setTotal(-s.getPrecoBase());
                    System.out.println("Serviço removido da compra!");
                    removido = true;
                    break;
                }
            }

            if (!removido) {
                for (int i = 0; i < compra.getPecas().tamanhoLista(); i++) {
                    Peca p = compra.getPecas().getDado(i);
                    if (p.getIdProduto() == idBusca) {
                        compra.getPecas().removeMeio(i);
                        compra.setTotal(-p.getPrecoBase());
                        System.out.println("Peça removida da compra!");
                        removido = true;
                        break;
                    }
                }
            }

        } catch (NumberFormatException e) {
            for (int i = 0; i < compra.getPecas().tamanhoLista(); i++) {
                Peca p = compra.getPecas().getDado(i);
                if (p.getNome().equalsIgnoreCase(entrada)) {
                    compra.getPecas().removeMeio(i);
                    compra.setTotal(-p.getPrecoBase());
                    System.out.println("Peça removida da compra!");
                    removido = true;
                    break; // Sai do for
                }
            }
        }

        if (!removido) {
            System.out.println("Este item não foi encontrado dentro desta compra.");
        }
    }
}
