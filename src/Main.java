import controller.*;
import model.*;
import services.ListaSimplesDinamica;
import view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ListaSimplesDinamica<Carro> listaCarros = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Peca> listaPecas = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Servico> listaServicos = new ListaSimplesDinamica<>();

        CarroController carroCtrl = new CarroController(listaCarros);
        PecaController pecaCtrl = new PecaController(listaPecas);
        ServicoController servicoCtrl = new ServicoController(listaServicos);

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n=======================================");
            System.out.println("      SISTEMA DE GESTÃO - AUTO PEÇAS     ");
            System.out.println("=======================================");
            System.out.println("1. Peças (Página)");
            System.out.println("2. Carro (Página)");
            System.out.println("3. Marcas de Carro (Página)");
            System.out.println("4. Marca de Peças (Página)");
            System.out.println("5. Serviços (Página)");
            System.out.println("6. Tipos de Peças (Página)");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma operação: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        PecaView uiPeca = new PecaView(pecaCtrl);
                        uiPeca.iniciar();
                        break;

                    case 2:
                        CarroController controller = new CarroController(listaCarros);
                        CarroView uiCarro = new CarroView(controller);
                        uiCarro.iniciar();
                        break;

                    case 3:
                        ListaSimplesDinamica<MarcaCarro> marcalistaSimpelsDinamica = new ListaSimplesDinamica<>();
                        MarcaCarroController marcaCtrl = new MarcaCarroController(marcalistaSimpelsDinamica);
                        MarcaCarroView uiMarca = new MarcaCarroView(marcaCtrl);
                        uiMarca.iniciar();
                        break;

                    case 4:
                        ListaSimplesDinamica<Marca> listaMarca = new ListaSimplesDinamica<>();
                        MarcaController marceCtrl = new MarcaController(listaMarca);
                        MarcaView marca = new MarcaView(marceCtrl);
                        marca.iniciar();
                        break;

                    case 5:
                        ServicoView servico = new ServicoView(servicoCtrl);
                        servico.iniciar();
                        break;

                    case 6:
                        ListaSimplesDinamica<Tipo> listaTipo = new ListaSimplesDinamica<>();
                        TipoController tipoCtrl = new TipoController(listaTipo);
                        TipoView tipo = new TipoView(tipoCtrl);
                        tipo.iniciar();
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