/*
 Projeto N1 - Controle de Estoque
 
 Nome: Natália Vaz Cerqueira | RA: 10779837
 Nome: Gabrielly Nogueira Rodrigues | RA: 10762966
 */

import java.util.Scanner;

public class ControleEstoque {
    public static void main(String[] args) {
        int capacidade = 100;

        String[] nomes = new String[capacidade];
        double[] precos = new double[capacidade];
        int[] quantidades = new int[capacidade];

        int totalProdutos = 0;

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("=== CONTROLE DE ESTOQUE ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Registrar inclusão de produto no estoque");
            System.out.println("3. Registrar retirada de produto do estoque");
            System.out.println("4. Consultar produto");
            System.out.println("5. Gerar relatório");
            System.out.println("6. Sair");
            System.out.print("Selecione uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    totalProdutos = cadastrarProduto(scanner, nomes, precos, quantidades, totalProdutos, capacidade);
                    break;
                case 2:
                    incluirEstoque(sc, nomes, quantidades, totalProdutos);
                    break;
                case 3:
                    retirarEstoque(sc, nomes, quantidades, totalProdutos);
                    break;
                case 4:
                    consultarProduto(sc, nomes, precos, quantidades, totalProdutos);
                    break;
                case 5:
                    gerarRelatorio(nomes, precos, quantidades, totalProdutos);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, digite novamente: ");
            }

        } while (opcao != 6);

    }

    public static int buscarProduto(String[] nomes, int totalProdutos, String nome) {
        for (int i = 0; i < totalProdutos; i++) {
            if (nomes[i].equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    public static int cadastrarProduto(Scanner scanner, String[] nomes, double[] precos, int[] quantidades, int totalProdutos, int capacidade) {
        if (totalProdutos >= capacidade) {
            System.out.println("Não é possível cadastrar essa quantidade de produtos, capacidade atingida.");
            return totalProdutos;
        }

        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        if (nome.equals("")) {
            System.out.println("O nome do produto não pode ser vazio.");
            return totalProdutos;
        }

        if (buscarProduto(nomes, totalProdutos, nome) != -1) {
            System.out.println("Produto cadastrado.");
            return totalProdutos;
        }

        System.out.print("Preço do produto: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        if (preco <= 0) {
            System.out.println("O preço do produto deve ser maior que zero.");
            return totalProdutos;
        }

        nomes[totalProdutos] = nome;
        precos[totalProdutos] = preco;
        quantidades[totalProdutos] = 0;

        System.out.println("Produto cadastrado com sucesso.");
        return totalProdutos + 1;
    }

    public static void incluirEstoque(Scanner scanner, String[] nomes, int[] quantidades, int totalProdutos) {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        int indice = buscarProduto(nomes, totalProdutos, nome);
        if (indice == -1) {
            System.out.println("Produto não cadastrado no sistema.");
            return;
        }

        System.out.print("Quantidade a ser adicionada ao estoque: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser positiva.");
            return;
        }

        quantidades[indice] += quantidade;
        System.out.println("Inclusão de estoque registrada! Quantidade atual: " + quantidades[indice]);
    }

    public static void retirarEstoque(Scanner scanner, String[] nomes, int[] quantidades, int totalProdutos) {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        int indice = buscarProduto(nomes, totalProdutos, nome);
        if (indice == -1) {
            System.out.println("Produto não cadastrado no sistema.");
            return;
        }

        System.out.print("Quantidade a ser retirada do estoque: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("A quantidade a ser retirada deve ser positiva.");
            return;
        }

        if (quantidade > quantidades[indice]) {
            System.out.println("Quantidade em estoque insuficiente! Estoque atual disponível: " + quantidades[indice]);
            return;
        }

        quantidades[indice] -= quantidade;
        System.out.println("Retirada de estoque registrada! Quantidade atual: " + quantidades[indice]);
    }

    public static void consultarProduto(Scanner sc, String[] nomes, double[] precos, int[] quantidades, int totalProdutos) {
        System.out.print("Nome do produto para consultar: ");
        String nome = sc.nextLine();

        int indice = buscarProduto(nomes, totalProdutos, nome);
        if (indice == -1) {
            System.out.println("O produto informado não existe.");
            return;
        }

        System.out.println("\n--- Detalhes do Produto ---");
        System.out.println("Nome: " + nomes[indice]);
        System.out.printf("Preço: R$ %.2f\n", precos[indice]);
        System.out.println("Quantidade atual em estoque: " + quantidades[indice]);
    }

    public static void gerarRelatorio(String[] nomes, double[] precos, int[] quantidades, int totalProdutos) {
        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado até ao momento.");
            return;
        }

        System.out.println("====== RELATÓRIO DE ESTOQUE ======");
        double valorTotalEstoque = 0.0;

        for (int i = 0; i < totalProdutos; i++) {
            double valorTotalProduto = precos[i] * quantidades[i];
            valorTotalEstoque += valorTotalProduto;

            System.out.printf("Produto: %s | Preço: R$ %.2f | Quantidade: %d | Valor Total: R$ %.2f\n",
                    nomes[i], precos[i], quantidades[i], valorTotalProduto);
        }

        System.out.println("------------------------------------------------------");
        System.out.printf("VALOR TOTAL DO ESTOQUE INTEIRO: R$ %.2f\n", valorTotalEstoque);
        System.out.println("======================================================");
    }
}
