package view;

import controller.MarcaCarroController;
import model.MarcaCarro;

import java.util.Scanner;

public class MarcaCarroView {

    private MarcaCarroController marcaCtrl;
    private Scanner scanner;

    public MarcaCarroView(MarcaCarroController marcaCtrl) {
        this.marcaCtrl = marcaCtrl;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n=== MÓDULO DE MARCAS DE CARRO ===");
            System.out.println("1 - Cadastrar nova Marca");
            System.out.println("2 - Remover Marca (por ID)");
            System.out.println("3 - Listar todas as Marcas");
            System.out.println("4 - Buscar Marca (por ID)");
            System.out.println("5 - Buscar Marca (por Nome)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarMarca();
                        break;
                    case 2:
                        removerMarca();
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
                    case 0:
                        System.out.println("Encerrando módulo de marcas...");
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

    private void cadastrarMarca() {
        System.out.println("\n--- Cadastrar Marca de Carro ---");
        System.out.print("Digite o Nome da Marca (ex: Chevrolet): ");
        String nomeMarca = scanner.nextLine();

        MarcaCarro novaMarca = new MarcaCarro(nomeMarca);

        marcaCtrl.create(novaMarca);
        System.out.println("Marca cadastrada com sucesso!");
    }

    private void removerMarca() {
        System.out.println("\n--- Remover Marca ---");
        System.out.print("Digite o ID da marca que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        marcaCtrl.remove(id);
        System.out.println("Comando de remoção executado.");
    }

    private void listarTodas() {
        System.out.println("\n--- Lista de Marcas ---");
        marcaCtrl.findAll();
    }

    private void buscarPorId() {
        System.out.println("\n--- Buscar Marca por ID ---");
        System.out.print("Digite o ID da marca: ");
        int id = Integer.parseInt(scanner.nextLine());

        MarcaCarro marca = marcaCtrl.findById(id);

        if (marca != null) {
            System.out.println(marca.toString());
        }

    }

    private void buscarPorNome() {
        System.out.println("\n--- Buscar Marca por Nome ---");
        System.out.print("Digite o nome da marca: ");
        String nome = scanner.nextLine();

        MarcaCarro marca = marcaCtrl.findByName(nome);

        if (marca != null) {
            System.out.println(marca.toString());
        }
    }
}