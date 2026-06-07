package view;

import controller.ServicoController;
import model.Servico;
import model.Tipo;

import java.util.Scanner;

public class ServicoView {

    private ServicoController servicoCtrl;
    private Scanner scanner;

    public ServicoView(ServicoController servicoCtrl) {
        this.servicoCtrl = servicoCtrl;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n=== SERVIÇOS ===");
            System.out.println("1 - Cadastrar novo Serviço");
            System.out.println("2 - Remover Serviço (por ID)");
            System.out.println("3 - Listar todos os Serviços");
            System.out.println("4 - Buscar Serviço (por ID)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarServico();
                        break;
                    case 2:
                        removerServico();
                        break;
                    case 3:
                        listarTodos();
                        break;
                    case 4:
                        buscarPorId();
                        break;
                    case 0:
                        System.out.println("Encerrando módulo de serviços...");
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

    private void cadastrarServico() {
        System.out.println("\n--- Cadastrar Serviço ---");

        System.out.print("Digite o nome do Serviço (ex: Instalação motor): ");
        String nomeServico = scanner.nextLine();

        System.out.print("Digite o Tipo de Serviço (ex: Instalacao): ");
        String nomeTipo = scanner.nextLine();

        System.out.print("Digite a duração do serviço em horas (ex: 3): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o Preço Base (Apenas números, ex: 3000): ");
        int precoBase = Integer.parseInt(scanner.nextLine());

        Tipo novoTipo = new Tipo(nomeTipo);

        Servico novoServico = new Servico(nomeServico, novoTipo, duracao, precoBase);

        servicoCtrl.create(novoServico);
    }

    private void removerServico() {
        System.out.println("\n--- Remover Serviço ---");
        System.out.print("Digite o ID do serviço que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        servicoCtrl.remove(id);
    }

    private void listarTodos() {
        System.out.println("\n--- Lista de Serviços ---");
        servicoCtrl.findAll();
    }

    private void buscarPorId() {
        System.out.println("\n--- Buscar Serviço por ID ---");
        System.out.print("Digite o ID do serviço: ");
        int id = Integer.parseInt(scanner.nextLine());

        Servico s = servicoCtrl.findById(id);
        if (s != null) {
            System.out.println("Serviço encontrado: " + s.getNome() + " | Duração: " + s.getDuracao() + "h");
        }
    }
}