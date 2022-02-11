package model;

import helper.Utils;

import java.util.Date;

public class Conta {
    private static int codigo = 1001;

    private  int numero;
    private Cliente cliente;
    private Double saldo = 0.0;
    private Double limite = 0.0;
    private Double saldoTotal = 0.0;

    public Conta(Cliente cliente) {
        this.numero = Conta.codigo;
        this.cliente = cliente;
        Conta.codigo++;
        this.atualizaSaldoTotal();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
        this.atualizaSaldoTotal();
    }

    public Double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }


    private void atualizaSaldoTotal() {
        this.saldoTotal = this.getSaldo() + this.getLimite();
    }

    public String toString() {
        return "Numero da conta: " + this.getNumero() +
                "\nCliente: " + this.cliente.getNome() +
                "\nSaldo total: " + Utils.doubleParaString(this.getSaldoTotal());

    }

    public void depositar(Double vl) {
        if (vl > 0) {
            this.saldo = this.getSaldo() + vl;
            this.atualizaSaldoTotal();
            System.out.println("Depósito efetuado com sucesso na conta: " + this.numero + " valor: " + vl);
        } else {
            System.out.println("Erro ao efeturar deposito. Tente novamente.");
        }
    }

    public void sacar(Double vl) {
        if (vl > 0 && this.getSaldoTotal() >= vl) {
            if (this.getSaldo() >= vl) {
                this.saldo = this.getSaldo() - vl;
                this.atualizaSaldoTotal();
                System.out.println("Saque realizado com sucesso na conta: " + this.numero + " valor: " + vl);
            } else {
                Double restante = this.getSaldo() - vl;
                this.limite = this.getLimite() + restante;
                this.saldo = 0.0;
                this.atualizaSaldoTotal();
                System.out.println("Saque efetuado com sucesso na conta: " + this.numero + " valor: " + vl);
            }
        } else {
            System.out.println("Erro ao sacar. Tente novamente");
        }
    }

    public void transferir(Conta destino, Double valor) {
        if (valor > 0 && this.getSaldoTotal() >= valor) {
            if (this.getSaldo() >= valor) {
                this.saldo = this.getSaldo() - valor;
                destino.saldo = destino.getSaldo() + valor;
                this.atualizaSaldoTotal();
                destino.atualizaSaldoTotal();
                System.out.println("Transferencia realizada com sucesso para a conta: " + this.numero + " valor: " + valor);
            } else {
                Double restante = this.getSaldo() - valor;
                this.limite = this.getLimite() + restante;
                this.saldo = 0.0;
                destino.saldo = destino.getSaldo() + valor;
                this.atualizaSaldoTotal();
                destino.atualizaSaldoTotal();
                System.out.println("Transferencia realizada com sucesso para a conta: " + this.numero + " valor: " + valor);
            }
        } else {
            System.out.println("Transferencia não realizada. Tente novamente.");
        }
    }
}
