package view;

import helper.Utils;
import model.Cliente;
import model.Conta;

public class Teste {
    public static void main(String[] args) {

        Cliente felicity = new Cliente("Felicity", "felicity@gmail.com",
                "5468569475-54", Utils.stringParaDate("17/05/1981"));

        Cliente angelina = new Cliente("Angelina", "angelina@gmail.com",
                "258965478-12", Utils.stringParaDate("25/08/1991"));

//        System.out.println(felicity);
//        System.out.println();
//        System.out.println(angelina);

        Conta c101 = new Conta(felicity);
        Conta c102 = new Conta(angelina);

        // Depositando um valor positivo
        c101.depositar(500.0);
        c102.depositar(500.0);

//        c101.sacar(300.0);

        // Tentando sacar valor 0
//        c101.sacar(0.0);

        // Tentando sacar valor negativo
//        c101.sacar(-150.0);

        // Tentando sacar valor maior que o saldo
//        c101.sacar(700.0);

        // Setando um limite
        c101.setLimite(200.0);

        // Tentando sacar valor maior que o saldo mas agora tem limite
//        c101.sacar(600.0);


    }
}
