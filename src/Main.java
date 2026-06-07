import controller.*;
import model.*;
import services.ListaSimplesDinamica;
import view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Iniciando as estruturas de dados
        ListaSimplesDinamica<Carro> listaCarros = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> listaPecas = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Servico> listaServicos = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Compra> listaCompra = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<MarcaCarro> listaMarcaCarro = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Marca> listaMarca = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Tipo> listaTipo = new ListaSimplesDinamica<>();

        TipoController tipoCtrl = new TipoController(listaTipo);
        MarcaController marceCtrl = new MarcaController(listaMarca);
        MarcaCarroController marcaCarroCtrl = new MarcaCarroController(listaMarcaCarro);
        CompraController compraCtrl = new CompraController(listaCompra);
        CarroController carroCtrl = new CarroController(listaCarros);
        PecaController pecaCtrl = new PecaController(listaPecas);
        ServicoController servicoCtrl = new ServicoController(listaServicos);

        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal = -1;

        do {
            System.out.println("\n=======================================");
            System.out.println("     AUTO PEÇAS SIMAS     ");
            System.out.println("=======================================");
            System.out.println("1. Comprar");
            System.out.println("2. Estoque e Cadastros");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma operação: ");

            try {
                opcaoPrincipal = Integer.parseInt(scanner.nextLine());

                switch (opcaoPrincipal) {
                    case 1:
                        CompraView compra = new CompraView(compraCtrl, pecaCtrl, servicoCtrl);
                        compra.iniciar();
                        break;

                    case 2:
                        menuCadastros(scanner, pecaCtrl, carroCtrl, marcaCarroCtrl, marceCtrl, servicoCtrl, tipoCtrl);
                        break;

                    case 0:
                        System.out.println("Até logo!");
                        break;

                    default:
                        System.out.println("Opção inválida. Escolha um número do menu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números válidos.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }

        } while (opcaoPrincipal != 0);

        scanner.close();
    }

    private static void menuCadastros(Scanner scanner, PecaController pecaCtrl, CarroController carroCtrl,
                                      MarcaCarroController marcaCarroCtrl, MarcaController marceCtrl,
                                      ServicoController servicoCtrl, TipoController tipoCtrl) {
        int opcaoCadastro = -1;
        do {
            System.out.println("\n--- GERENCIAMENTO DE ESTOQUE E CADASTROS ---");
            System.out.println("1. Gerenciar Peças");
            System.out.println("2. Gerenciar Carros");
            System.out.println("3. Gerenciar Marcas de Carro");
            System.out.println("4. Gerenciar Marcas de Peças");
            System.out.println("5. Gerenciar Serviços");
            System.out.println("6. Gerenciar Tipos de Peças/Serviços");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma operação: ");

            try {
                opcaoCadastro = Integer.parseInt(scanner.nextLine());

                switch (opcaoCadastro) {
                    case 1:
                        new PecaView(pecaCtrl).iniciar();
                        break;
                    case 2:
                        new CarroView(carroCtrl).iniciar();
                        break;
                    case 3:
                        new MarcaCarroView(marcaCarroCtrl).iniciar();
                        break;
                    case 4:
                        new MarcaView(marceCtrl).iniciar();
                        break;
                    case 5:
                        new ServicoView(servicoCtrl).iniciar();
                        break;
                    case 6:
                        new TipoView(tipoCtrl).iniciar();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números válidos.");
            }
        } while (opcaoCadastro != 0);
    }
}