import java.util.Scanner;

import classes.Produto;

public class cadastroProduto {
    public static void main(String[] args) throws Exception {
        try (Scanner entrada = new Scanner(System.in)) {

            System.out.println("\nInforme seu nome completo: \n");
            String produto = entrada.nextLine();

            System.out.println("\nInforme seu número de celular: \n");
            String lote = entrada.nextLine();

            System.out.println("\nInforme seu CPF: (Com pontos e digito)) \n");
            String quantidade = entrada.nextLine();

            System.out.println("\nInforme sua senha: \n");
            String preco = entrada.nextLine();

            System.out.println("\nInforme o número de sua matrícula: \n");
            int Matricula = entrada.nextInt();

            Produto prod = new Produto(produto, lote, quantidade, preco);

            if (cadastroProduto(produto)) {
                System.out.println("\n Produto cadastrado com sucesso! \n");
            } else {
                System.err.println("\n O produto não foi cadasdrado! \n");
            }
        }
    }
}