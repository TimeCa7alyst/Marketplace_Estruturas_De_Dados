package view;

import controller.PecaController;
import model.*;

import java.util.Scanner;

public class PecaView {

    private PecaController pecaCtrl;
    private Scanner scanner;

    public PecaView(PecaController pecaCtrl) {
        this.pecaCtrl = pecaCtrl;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n=== PEÇAS ===");
            System.out.println("1 - Cadastrar nova Peça");
            System.out.println("2 - Remover Peça (por ID)");
            System.out.println("3 - Listar todas as Peças");
            System.out.println("4 - Buscar Peça (por ID)");
            System.out.println("5 - Buscar Peça (por Nome)");
            System.out.println("6 - Buscar Peça (por Marca da Peça)");
            System.out.println("7 - Buscar Peça (por Tipo)");
            System.out.println("8 - Buscar Peça (por Modelo do Carro)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarPeca();
                        break;
                    case 2:
                        removerPeca();
                        break;
                    case 3:
                        listarTodas();
                        break;
                    case 4:
                        buscarPorId();
                        break;
                    case 5:
                        buscarPorNome();
                        break;
                    case 6:
                        buscarPorMarca();
                        break;
                    case 7:
                        buscarPorTipo();
                        break;
                    case 8:
                        buscarPorCarro();
                        break;
                    case 0:
                        System.out.println("Encerrando módulo de peças...");
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

    private void cadastrarPeca() {
        System.out.println("\n--- Cadastrar Peça ---");

        System.out.print("Digite o nome da Peça (ex: Amortecedor): ");
        String nomePeca = scanner.nextLine();

        System.out.print("Digite a marca da Peça (ex: Bosch): ");
        String nomeMarca = scanner.nextLine();

        System.out.print("Digite o Tipo da Peça (ex: Suspensão): ");
        String nomeTipo = scanner.nextLine();

        System.out.print("Digite o Preço Base (Apenas números, ex: 3000): ");
        int precoBase = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o modelo do Carro compatível (ex: Gol): ");
        String modeloCarro = scanner.nextLine();

        System.out.print("Digite a marca do Carro compatível (ex: Volkswagen): ");
        String nomeMarcaCarro = scanner.nextLine();

        Marca novaMarca = new Marca(nomeMarca);
        MarcaCarro novaMarcaCarro = new MarcaCarro(nomeMarcaCarro);
        Carro novoCarro = new Carro(modeloCarro, novaMarcaCarro);
        Tipo novoTipo = new Tipo(nomeTipo);

        Peca novaPeca = new Peca(nomePeca, novaMarca, precoBase, novoCarro);
        novaPeca.setTipo(novoTipo);

        pecaCtrl.create(novaPeca);
    }

    private void removerPeca() {
        System.out.println("\n--- Remover Peça ---");
        System.out.print("Digite o ID da peça que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        pecaCtrl.remove(id);
    }

    private void listarTodas() {
        System.out.println("\n--- Lista de Peças ---");
        pecaCtrl.findAll();
    }

    private void buscarPorId() {
        System.out.println("\n--- Buscar Peça por ID ---");
        System.out.print("Digite o ID da peça: ");
        int id = Integer.parseInt(scanner.nextLine());

        Peca p = pecaCtrl.findById(id);
        if (p != null) {
            System.out.println("Peça encontrada: " + p.getNome() + " | Tipo: " + p.getTipo().getNome() + " | Marca: " + p.getMarca().getNome() +
                    " | Compatível com: " + p.getModeloCarro().getMarca().getNome() + " " + p.getModeloCarro().getNome() + " | Valor: R$" + p.getPrecoBase());
        }
    }

    private void buscarPorNome() {
        System.out.println("\n--- Buscar Peça por Nome ---");
        System.out.print("Digite o nome da peça: ");
        String nome = scanner.nextLine();

        Peca p = pecaCtrl.findByName(nome);
        if (p != null) {
            System.out.println("Peça encontrada: " + p.getNome() + " | Tipo: " + p.getTipo().getNome() + " | Marca: " + p.getMarca().getNome() +
                    " | Compatível com: " + p.getModeloCarro().getMarca().getNome() + " " + p.getModeloCarro().getNome() + " | Valor: R$" + p.getPrecoBase());
        }
    }

    private void buscarPorMarca() {
        System.out.println("\n--- Buscar Peça por Marca ---");
        System.out.print("Digite a marca da peça (ex: Bosch): ");
        String marca = scanner.nextLine();

        Peca p = pecaCtrl.findByMarca(marca);
        if (p != null) {
            System.out.println("Peça encontrada: " + p.getNome() + " | Tipo: " + p.getTipo().getNome() + " | Marca: " + p.getMarca().getNome() +
                    " | Compatível com: " + p.getModeloCarro().getMarca().getNome() + " " + p.getModeloCarro().getNome() + " | Valor: R$" + p.getPrecoBase());
        }
    }

    private void buscarPorTipo() {
        System.out.println("\n--- Buscar Peça por Tipo ---");
        System.out.print("Digite o tipo da peça (ex: Suspensão): ");
        String tipo = scanner.nextLine();

        Peca p = pecaCtrl.findByTipo(tipo);
        if (p != null) {
            System.out.println("Peça encontrada: " + p.getNome() + " | Tipo: " + p.getTipo().getNome() + " | Marca: " + p.getMarca().getNome() +
                    " | Compatível com: " + p.getModeloCarro().getMarca().getNome() + " " + p.getModeloCarro().getNome() + " | Valor: R$" + p.getPrecoBase());
        }
    }

    private void buscarPorCarro() {
        System.out.println("\n--- Buscar Peça por Carro ---");
        System.out.print("Digite o modelo do carro (ex: Gol): ");
        String carro = scanner.nextLine();

        Peca p = pecaCtrl.findByCarro(carro);
        if (p != null) {
            System.out.println("Peça encontrada: " + p.getNome() + " | Tipo: " + p.getTipo().getNome() + " | Marca: " + p.getMarca().getNome() +
                    " | Compatível com: " + p.getModeloCarro().getMarca().getNome() + " " + p.getModeloCarro().getNome() + " | Valor: R$" + p.getPrecoBase());
        }
    }
}