import controller.CarroController;
import controller.PecaController;
import estruturas.ListaSimplesDinamica;
import model.Carro;
import model.Peca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ListaSimplesDinamica<Carro> listaCarros = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> listaPecas = new ListaSimplesDinamica<>();


        CarroController carroCtrl = new CarroController(listaCarros);
        PecaController pecaCtrl = new PecaController(listaPecas);

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n=== SISTEMA DE AUTO PEÇAS ===");
            System.out.println("1. Consultar Catálogo de Peças");
            System.out.println("2. Consultar Carros Cadastrados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        pecaCtrl.findAll();
                        break;
                    case 2:
                        carroCtrl.findAll();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (NumberFormatException e) {

                System.out.println("Erro: Entrada inválida. Por favor, digite apenas números.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}