package ACT05;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double minSaldo;

    // Constructor de una cuenta de ahorros con saldo 0
    public CuentaAhorro(int numeroCuenta) {
        super(numeroCuenta, 0);
        this.tasaInteres = 1.0; // valor por defecto (%)
        this.minSaldo = 0;
    }

    public void setTasaInteres(double interes) {
        this.tasaInteres = interes;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    @Override
    public void retirar(double monto) {
        super.retirar(monto);
        double saldoActual = getSaldo();
        if (saldoActual < minSaldo) {
            minSaldo = saldoActual;
        }
    }

    @Override
    public void consultar() {
        // Se depositan los intereses calculados sobre el saldo mínimo del mes
        double interes = minSaldo * tasaInteres / 100.0;
        depositar(interes);
        // Se restablece el saldo mínimo para el siguiente mes
        minSaldo = getSaldo();
    }
}