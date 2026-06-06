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
        ListaSimplesDinamica<Compra> listaCompra = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<MarcaCarro> marcalistaSimpelsDinamica = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Marca> listaMarca = new ListaSimplesDinamica<>();
        ListaSimplesDinamica<Tipo> listaTipo = new ListaSimplesDinamica<>();

        TipoController tipoCtrl = new TipoController(listaTipo);
        MarcaController marceCtrl = new MarcaController(listaMarca);
        MarcaCarroController marcaCtrl = new MarcaCarroController(marcalistaSimpelsDinamica);
        CompraController compraCtrl = new CompraController(listaCompra);
        CarroController carroCtrl = new CarroController(listaCarros);
        PecaController pecaCtrl = new PecaController(listaPecas);
        ServicoController servicoCtrl = new ServicoController(listaServicos);
        MarcaCarroController marcaCarroCtrl = new MarcaCarroController(listaMarcaCarro);
        MarcaController marcaCtrl = new MarcaController(listaMarca);
        TipoController tipoCtrl = new TipoController(listaTipo);

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
            System.out.println("7. Compra (Página)");
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
                        CarroView uiCarro = new CarroView(carroCtrl);
                        uiCarro.iniciar();
                        break;

                    case 3:
                        MarcaCarroView uiMarca = new MarcaCarroView(marcaCtrl);
                        uiMarca.iniciar();
                        break;

                    case 4:
                        MarcaView uiMarca = new MarcaView(marceCtrl);
                        marca.iniciar();
                        break;

                    case 5:
                        ServicoView uiServico = new ServicoView(servicoCtrl);
                        uiServico.iniciar();
                        break;

                    case 6:
                        TipoView uiTipo = new TipoView(tipoCtrl);
                        uiTipo.iniciar();
                        break;

                    case 7:
                        CompraView compra = new CompraView(compraCtrl, pecaCtrl, servicoCtrl);
                        compra.iniciar();
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