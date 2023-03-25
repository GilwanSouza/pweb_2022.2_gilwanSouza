import java.util.Scanner;

import classes.Cliente;

public class cadastroCliente {
    public static void main(String[] args) throws Exception {
        try (Scanner entrada = new Scanner(System.in)) {

            System.out.println("\nInforme seu nome completo: \n");
            String Nome = entrada.nextLine();

            System.out.println("\nInforme seu número de celular: \n");
            String Numero = entrada.nextLine();

            System.out.println("\nInforme seu CPF: (Com pontos e digito)) \n");
            String Cpf = entrada.nextLine();

            System.out.println("\nInforme sua senha: \n");
            String Senha = entrada.nextLine();

            System.out.println("\nInforme o número de sua matrícula: \n");
            int Matricula = entrada.nextInt();

            Cliente cliente = new Cliente(Nome, Numero, Cpf, Senha);

            if (cadastroCliente(cliente)) {
                System.out.println("\n Cliente cadastrado com sucesso! \n");
            } else {
                System.err.println("\n O cliente não foi cadasdrado! \n");
            }
        }
    }
}