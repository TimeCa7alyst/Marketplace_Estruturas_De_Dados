import controller.CarroController;
import controller.PecaController;
import controller.ServicoController;
import estruturas.ListaSimplesDinamica;
import model.Carro;
import model.MarcaCarro;
import model.Peca;
import model.Servico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 1. Instanciar as Listas Base (O "Disco" da nossa Base de Dados)
        ListaSimplesDinamica<Carro> listaCarros = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> listaPecas = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Servico> listaServicos = new ListaSimplesDinamica<>();

        // 2. Injetar as dependências nos Controllers (Isto vai disparar os dadosMock)
        CarroController carroCtrl = new CarroController(listaCarros);
        PecaController pecaCtrl = new PecaController(listaPecas);
        ServicoController servicoCtrl = new ServicoController(listaServicos);

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n=======================================");
            System.out.println("      SISTEMA DE GESTÃO - AUTO PEÇAS     ");
            System.out.println("=======================================");
            System.out.println("1. Listar Catálogo de Peças (Lista Simples)");
            System.out.println("2. Listar Carros Cadastrados (Lista Simples)");
            System.out.println("3. Buscar Peça por NOME (Árvore Binária)");
            System.out.println("4. Buscar Carro por ID (Árvore Binária)");
            System.out.println("5. Cadastrar Novo Carro");
            System.out.println("6. Remover Carro por ID");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma operação: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- CATÁLOGO DE PEÇAS ---");
                        pecaCtrl.findAll(); // Operação O(N)
                        break;

                    case 2:
                        System.out.println("\n--- CARROS CADASTRADOS ---");
                        carroCtrl.findAll(); // Operação O(N)
                        break;

                    case 3:
                        System.out.print("\nDigite o NOME da peça a buscar (ex: Amortecedor): ");
                        String nomePeca = scanner.nextLine();
                        System.out.println("A pesquisar nos índices da Árvore Binária...");
                        Peca pecaEncontrada = pecaCtrl.findByName(nomePeca); // Operação O(log N)

                        if (pecaEncontrada != null) {
                            System.out.println(">> SUCESSO: " + pecaEncontrada.toString());
                        }
                        break;

                    case 4:
                        System.out.print("\nDigite o ID do carro a buscar (ex: 1 ou 2): ");
                        int idCarroBusca = Integer.parseInt(scanner.nextLine());
                        System.out.println("A pesquisar nos índices da Árvore Binária...");
                        Carro carroEncontrado = carroCtrl.findById(idCarroBusca); // Operação O(log N)

                        if (carroEncontrado != null) {
                            System.out.println(">> SUCESSO: " + carroEncontrado.toString());
                        }
                        break;

                    case 5:
                        System.out.println("\n--- NOVO REGISTO DE CARRO ---");
                        System.out.print("Digite o ID numérico do carro: ");
                        int idNovo = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o Modelo do carro (ex: Celta): ");
                        String modeloNovo = scanner.nextLine();

                        System.out.print("Digite o ID da Marca: ");
                        int idMarca = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o Nome da Marca (ex: Chevrolet): ");
                        String nomeMarca = scanner.nextLine();

                        // Criação das instâncias
                        MarcaCarro novaMarca = new MarcaCarro(idMarca, nomeMarca);
                        Carro novoCarro = new Carro(idNovo, modeloNovo, novaMarca);

                        carroCtrl.create(novoCarro); // Insere na Lista E nas Árvores!
                        break;

                    case 6:
                        System.out.print("\nDigite o ID do carro que deseja remover: ");
                        int idRemover = Integer.parseInt(scanner.nextLine());
                        carroCtrl.remove(idRemover);
                        break;

                    case 0:
                        System.out.println("A encerrar o sistema... Até logo!");
                        break;

                    default:
                        System.out.println("⚠️ Opção inválida. Escolha um número do menu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Erro de input: Por favor, digite apenas números válidos.");
            } catch (Exception e) {
                System.out.println("⚠️ Ocorreu um erro inesperado: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }
}