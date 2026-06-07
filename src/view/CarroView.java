package view;

import controller.CarroController;
import model.Carro;
import model.MarcaCarro;

import java.util.Scanner;

public class CarroView {

    private CarroController carroCtrl;
    private Scanner scanner;

    public CarroView(CarroController carroCtrl) {
        this.carroCtrl = carroCtrl;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n=== CARROS ===");
            System.out.println("1 - Cadastrar novo Carro");
            System.out.println("2 - Remover Carro (por ID)");
            System.out.println("3 - Listar todos os Carros");
            System.out.println("4 - Buscar Carro (por ID)");
            System.out.println("5 - Buscar Carro (por Nome)");
            System.out.println("6 - Buscar Carro (por Marca)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarCarro();
                        break;
                    case 2:
                        removerCarro();
                        break;
                    case 3:
                        listarTodos();
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
                    case 0:
                        System.out.println("Encerrando módulo de carros...");
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

    private void cadastrarCarro() {
        System.out.println("\n--- Cadastrar Carro ---");
        System.out.print("Digite o Modelo do carro (ex: Celta): ");
        String modeloNovo = scanner.nextLine();

        System.out.print("Digite o Nome da Marca (ex: Chevrolet): ");
        String nomeMarca = scanner.nextLine();

        MarcaCarro novaMarca = new MarcaCarro(nomeMarca);
        Carro novoCarro = new Carro(modeloNovo, novaMarca);

        carroCtrl.create(novoCarro);
        System.out.println("Carro cadastrado com sucesso!");
    }

    private void removerCarro() {
        System.out.println("\n--- Remover Carro ---");
        System.out.print("Digite o ID do carro que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        carroCtrl.remove(id);
        System.out.println("Comando de remoção executado.");
    }

    private void listarTodos() {
        System.out.println("\n--- Lista de Carros ---");
        carroCtrl.findAll();
    }

    private void buscarPorId() {
        System.out.println("\n--- Buscar Carro por ID ---");
        System.out.print("Digite o ID do carro: ");
        int id = Integer.parseInt(scanner.nextLine());

        Carro carro = carroCtrl.findById(id);

        if (carro != null) {
            System.out.println(carro.toString());
        }
    }

    private void buscarPorNome() {
        System.out.println("\n--- Buscar Carro por Nome ---");
        System.out.print("Digite o nome (modelo) do carro: ");
        String nome = scanner.nextLine();

        Carro carro = carroCtrl.findByName(nome);
        System.out.println(carro.toString());
    }

    private void buscarPorMarca() {
        System.out.println("\n--- Buscar Carro por Marca ---");
        System.out.print("Digite o nome da marca: ");
        String marca = scanner.nextLine();

        Carro carro = carroCtrl.findByMarca(marca);
        System.out.println(carro.toString());
    }
}