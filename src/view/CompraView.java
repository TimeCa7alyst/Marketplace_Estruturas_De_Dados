package view;

import controller.CompraController;
import controller.MarcaController;
import controller.PecaController;
import controller.ServicoController;
import model.Compra;
import model.Marca;
import model.Peca;
import model.Servico;

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
            System.out.println("\n=== COMPRAR ===");
            System.out.println("1 - Realizar Nova Compra");
            System.out.println("2 - Listar todas as Compras");
            System.out.println("3 - Buscar Compra (por ID)");
            System.out.println("4 - Editar Compra");
            System.out.println("5 - Excluir Compra (por ID)");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

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
                        System.out.println("Saindo do modulo de compras...");
                        break;
                    default:
                        System.out.println("Opcao invalida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void cadastrarCompra() {
        System.out.println("\n--- Nova Compra Aberta ---");

        Compra novaCompra = new Compra();
        boolean operandoCaixa = true;

        while (operandoCaixa) {
            System.out.println("\n=================================");
            System.out.println("CARRINHO: " + (novaCompra.getPecas().tamanhoLista() + novaCompra.getServicos().tamanhoLista()) + " itens");
            System.out.println("SUBTOTAL: R$ " + novaCompra.getTotal());
            System.out.println("=================================\n");
            System.out.println("1 - Inserir item no carrinho (Por ID ou Nome)");
            System.out.println("2 - Consultar Catalogo de Peças");
            System.out.println("3 - Consultar Catalogo de Serviços");
            System.out.println("4 - Remover item do carrinho (Por ID ou Nome)");
            System.out.println("5 - Listar itens do carrinho");
            System.out.println("0 - Concluir Pagamento / Fechar Compra");
            System.out.println("9 - Cancelar Compra");
            System.out.print("Acao desejada: ");

            String acao = scanner.nextLine().trim();

            switch (acao) {
                case "1":
                    inserirItem(novaCompra);
                    break;
                case "2":
                    menuConsultaPecas();
                    break;
                case "3":
                    menuConsultaServicos();
                    break;
                case "4":
                    removerItemNaEdicao(novaCompra);
                    break;
                case "5":
                    listarItens(novaCompra);
                    break;
                case "0":
                    if (novaCompra.getPecas().listaVazia() && novaCompra.getServicos().listaVazia()) {
                        System.out.println("Carrinho vazio. Compra nao foi registrada.");
                    } else {
                        compraCtrl.create(novaCompra);
                        System.out.println("\nCompra finalizada com sucesso!");
                        System.out.println("Valor Final da Compra: R$ " + novaCompra.getTotal());
                    }
                    operandoCaixa = false;
                    break;
                case "9":
                    System.out.println("Operacao cancelada. Carrinho esvaziado.");
                    operandoCaixa = false;
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void inserirItem(Compra compra) {
        System.out.print("\nDigite o ID ou Nome exato da peça ou serviço: ");
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

            if (!itemEncontrado) {
                Servico servico = servicoCtrl.findByName(entrada);
                if (servico != null) {
                    compra.getServicos().insere(servico);
                    compra.setTotal(servico.getPrecoBase());
                    System.out.println("Serviço adicionado!");
                    itemEncontrado = true;
                }
            }
        }

        if (!itemEncontrado) {
            System.out.println("Nenhum item encontrado com esse identificador. Tente consultar o estoque (opcoes 2 ou 3).");
        }
    }

    private void menuConsultaPecas() {
        System.out.println("\n--- Consulta de Peças ---");
        System.out.println("1 - Listar Todas as Peças");
        System.out.println("2 - Buscar por Nome");
        System.out.println("3 - Buscar por Marca");
        System.out.println("4 - Buscar por Carro Compativel");
        System.out.println("0 - Voltar ao Caixa");
        System.out.print("Escolha a busca: ");

        String op = scanner.nextLine();

        switch (op) {
            case "1":
                pecaCtrl.findAll();
                break;
            case "2":
                System.out.print("Digite o nome da peça: ");
                String nome = scanner.nextLine();
                Peca p = pecaCtrl.findByName(nome);
                if (p != null) System.out.println(p.toString());
                break;
            case "3":
                System.out.println("Digite a marca da peça");
                String marca = scanner.nextLine();
                Peca p3 = pecaCtrl.findByMarca(marca);
                if (p3 != null) System.out.println(p3.toString());
                break;
            case "4":
                System.out.print("Digite o modelo do carro (ex: Gol): ");
                String carro = scanner.nextLine();
                Peca p2 = pecaCtrl.findByCarro(carro);
                if (p2 != null) System.out.println(p2.toString());
                break;
            case "0":
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private void menuConsultaServicos() {
        System.out.println("\n--- Consulta de Serviços ---");
        servicoCtrl.findAll();
    }

    private void buscarPorId() {
        System.out.println("\n--- Buscar Compra por ID ---");
        System.out.print("Digite o ID da compra: ");
        int id = Integer.parseInt(scanner.nextLine());

        Compra compra = compraCtrl.findById(id);
        if (compra != null) {
            System.out.println(compra.toString());
        }
    }

    private void listarTodos() {
        System.out.println("\n--- Registro de Compras ---");
        compraCtrl.findAll();
    }

    private void listarItens(Compra compra) {
        System.out.println("\n--- Itens do Carrinho ---");

        System.out.println("\n| Pecas |");

        if (compra.getPecas() != null && !compra.getPecas().listaVazia()) {
            compra.getPecas().printLista();
        } else {
            System.out.println("Nenhuma peça no carrinho");
        }

        System.out.println("\n| Servicos |");
        if (compra.getServicos() != null && !compra.getServicos().listaVazia()) {
            compra.getServicos().printLista();
        } else {
            System.out.println("Nenhum serviço no carrinho");
        }
    }

    private void excluirCompra() {
        System.out.println("\n--- Excluir Compra ---");
        System.out.print("Digite o ID da compra que deseja excluir: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            compraCtrl.remove(id);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor, digite um ID numerico valido.");
        }
    }

    private void editarCompra() {
        System.out.println("\n--- Editar Compra ---");
        System.out.print("Digite o ID da compra que deseja editar: ");

        int idCompra;
        try {
            idCompra = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID invalido.");
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
            System.out.println("3 - Consultar Catalogo de Peças (Estoque)");
            System.out.println("4 - Consultar Catalogo de Serviços");
            System.out.println("5 - Listar Itens da Compra");
            System.out.println("0 - Concluir Edicao");
            System.out.print("Escolha uma opcao: ");

            String opcaoEdit = scanner.nextLine();

            switch (opcaoEdit) {
                case "1":
                    inserirItem(compra);
                    break;
                case "2":
                    removerItemNaEdicao(compra);
                    break;
                case "3":
                    menuConsultaPecas();
                    break;
                case "4":
                    menuConsultaServicos();
                    break;
                case "5":
                    listarItens(compra);
                case "0":
                    editando = false;
                    System.out.println("Edicao concluida com sucesso!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }

            if (compra.getPecas().listaVazia() && compra.getServicos().listaVazia()) {
                System.out.println("\nA compra ficou sem nenhum item (peças ou serviços).");
                System.out.println("Excluindo a compra automaticamente do sistema...");
                compraCtrl.remove(compra.getIdCompra());
                editando = false;
            }
        }
    }

    private void removerItemNaEdicao(Compra compra) {
        System.out.print("Digite o ID ou Nome do item presente no carrinho para REMOVER: ");
        String entrada = scanner.nextLine().trim();
        boolean removido = false;

        try {
            int idBusca = Integer.parseInt(entrada);

            for (int i = 0; i < compra.getServicos().tamanhoLista(); i++) {
                Servico s = compra.getServicos().getDado(i);
                if (s.getIdProduto() == idBusca) {
                    compra.getServicos().removeMeio(i);
                    compra.setTotal(-s.getPrecoBase());
                    System.out.println("Servico removido do carrinho");
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
                        System.out.println("Peca removida do carrinho");
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
                    System.out.println("Peca removida do carrinho");
                    removido = true;
                    break;
                }
            }

            if (!removido) {
                for (int i = 0; i < compra.getServicos().tamanhoLista(); i++) {
                    Servico s = compra.getServicos().getDado(i);
                    if (s.getNome().equalsIgnoreCase(entrada)) {
                        compra.getServicos().removeMeio(i);
                        compra.setTotal(-s.getPrecoBase());
                        System.out.println("Servico removido do carrinho");
                        removido = true;
                        break;
                    }
                }
            }
        }

        if (!removido) {
            System.out.println("Este item nao foi encontrado dentro do carrinho.");
        }
    }
}