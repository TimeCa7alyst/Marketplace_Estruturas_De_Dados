package view;

import controller.TipoController;
import model.Tipo;

import java.util.Scanner;

public class TipoView {

    private TipoController tipoCtrl;
    private Scanner scanner;

    public TipoView(TipoController tipoCtrl) {
        this.tipoCtrl = tipoCtrl;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n=== MÓDULO DE TIPOS ===");
            System.out.println("1 - Cadastrar novo Tipo");
            System.out.println("2 - Remover Tipo (por ID)");
            System.out.println("3 - Listar todos os Tipos");
            System.out.println("4 - Buscar Tipo (por Nome)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarTipo();
                        break;
                    case 2:
                        removerTipo();
                        break;
                    case 3:
                        listarTodos();
                        break;
                    case 4:
                        buscarPorNome();
                        break;
                    case 0:
                        System.out.println("Encerrando módulo de tipos...");
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

    private void cadastrarTipo() {
        System.out.println("\n--- Cadastrar Tipo ---");

        System.out.print("Digite o nome do Tipo (ex: Motores): ");
        String nomeTipo = scanner.nextLine();

        Tipo novoTipo = new Tipo(nomeTipo);

        tipoCtrl.create(novoTipo);
    }

    private void removerTipo() {
        System.out.println("\n--- Remover Tipo ---");
        System.out.print("Digite o ID do tipo que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        tipoCtrl.remove(id);
    }

    private void listarTodos() {
        System.out.println("\n--- Lista de Tipos ---");
        tipoCtrl.findAll();
    }

    private void buscarPorNome() {
        System.out.println("\n--- Buscar Tipo por Nome ---");
        System.out.print("Digite o nome do tipo: ");
        String nome = scanner.nextLine();

        Tipo t = tipoCtrl.findByName(nome);
        if (t != null) {
            System.out.println("Tipo encontrado: " + t.getNome());
        }
    }
}