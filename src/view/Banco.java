package view;

import helper.Utils;
import model.Cliente;
import model.Conta;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    static String nome = "Geek Bank";
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Conta> contas;

    public static void main(String[] args) {
        Banco.contas = new ArrayList<Conta>();
        Banco.menu();
    }

    public static void menu() {
        int opcao = 0;

        System.out.println("------------------------------------");
        System.out.println("[1] - Criar conta");
        System.out.println("[2] - Efetuar saque");
        System.out.println("[3] - Efetuar deposito");
        System.out.println("[4] - Efetuar transferencia");
        System.out.println("[5] - Listar contas");
        System.out.println("[6] - Sair do sistema");

        try {
            opcao = Integer.parseInt(Banco.teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Informe uma opção valida.");
            Utils.pauser(1);
            Banco.menu();
        }

        switch (opcao) {
            case 1 -> Banco.criarConta();
            case 2 -> Banco.efetuarSaque();
            case 3 -> Banco.efetuarDeposito();
            case 4 -> Banco.efetuarTransferencia();
            case 5 -> Banco.listarContas();
            case 6 -> {
                System.out.println("Saindo");
                Utils.pauser(2);
                System.exit(0);
            }
            default -> {
                System.out.println("Opção invalida.");
                Utils.pauser(2);
                Banco.menu();
            }
        }
    }

    public static void criarConta() {
        System.out.println("Criando conta");
        System.out.println("Informe os dados do cliente");

        System.out.println("Nome: ");
        String nome = Banco.teclado.nextLine();
        System.out.println("E-mail: ");
        String email = Banco.teclado.nextLine();
        System.out.println("CPF: ");
        String cpf = Banco.teclado.nextLine();
        System.out.println("Data de nascimento: ");
        String dataNascimento = Banco.teclado.nextLine();

        Cliente cliente = new Cliente(nome, email, cpf, Utils.stringParaDate(dataNascimento));

        Conta conta = new Conta(cliente);

        Banco.contas.add(conta);

        System.out.println("Conta criada com sucesso.");
        System.out.println("Dados da conta criada");
        System.out.println(conta);
        System.out.println();

        Utils.pauser(4);
        Banco.menu();
    }

    public static void efetuarSaque() {
        System.out.println("Efetuando saque");

        System.out.println("Informe o número da conta");
        int numero = Banco.teclado.nextInt();

        Conta conta = Banco.buscarContaPorNumero(numero);

        if (conta != null) {
            System.out.println("Informe o valor para saque: ");
            Double valor = Banco.teclado.nextDouble();

            conta.sacar(valor);
        } else {
            System.out.println("Não foi encontrada a conta número: " + numero);
        }

        Utils.pauser(3);
        Banco.menu();
    }

    public static void efetuarDeposito() {
        System.out.println("Efetuando deposito");

        System.out.println("Informe o número da conta: ");
        int numero = Banco.teclado.nextInt();

        Conta conta = Banco.buscarContaPorNumero(numero);

        if (conta != null) {
            System.out.println("Informe o valor de depósito: ");
            Double valor = Banco.teclado.nextDouble();

            conta.depositar(valor);
        } else {
            System.out.println("Não foi encontrada a conta número: " + numero);
        }

        Utils.pauser(3);
        Banco.menu();
    }

    public static void efetuarTransferencia() {
        System.out.println("Efetuando transferencia");

        System.out.println("Informe o número da sua conta: ");
        int numeroContaOrigem = Banco.teclado.nextInt();

        Conta contaOrigem = Banco.buscarContaPorNumero(numeroContaOrigem);

        if (contaOrigem != null) {
            System.out.println("Informe o número da conta destino: ");
            int numeroDestino = Banco.teclado.nextInt();

            Conta contaDestino = Banco.buscarContaPorNumero(numeroDestino);

            if (contaDestino != null) {
                System.out.println("Informe o valor da transferencia: ");
                Double valor = Banco.teclado.nextDouble();

                contaOrigem.transferir(contaDestino, valor);
            } else {
                System.out.println("A conta destino número: " + numeroDestino + " não foi encontrada.");
            }
        } else {
            System.out.println("Não foi encontrado a conta: " + numeroContaOrigem);
        }

        Utils.pauser(3);
        Banco.menu();
    }

    public static void listarContas() {
        System.out.println("Lista de contas");

        if (Banco.contas.size() > 0) {
            System.out.println("Listagem de contas");

            for (Conta conta : Banco.contas) {
                System.out.println(conta);
                System.out.println();

                Utils.pauser(3);
            }
        } else {
            System.out.println("Não existem contas cadastradas ainda");
        }

        Utils.pauser(3);
        Banco.menu();
    }

    private static Conta buscarContaPorNumero(int numero) {
        Conta c = null;

        if (Banco.contas.size() > 0) {
            for (Conta conta : Banco.contas) {
                if (conta.getNumero() == numero) {
                    c = conta;
                }
            }
        }

        return c;
    }
}
