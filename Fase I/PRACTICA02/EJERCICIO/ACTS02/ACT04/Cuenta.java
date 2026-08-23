package ACT04;

public class Cuenta {
    private int numero;
    private double saldo;

    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public Cuenta(int numero) {
        this(numero, 0);
    }

    public int getNumCuenta() {
        return numero;
    }

    public void setNumCuenta(int numCuenta) {
        this.numero = numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuenta N°" + numero + " - Saldo: S/. " + saldo;
    }
}