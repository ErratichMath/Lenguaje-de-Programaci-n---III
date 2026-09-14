public class CuentaBancaria {
    protected String numeroCuenta;
    protected String titular;
    protected double saldo;
    protected boolean cerrada;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.cerrada = false;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        saldo += monto;
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retirar " + monto);
        }
        saldo -= monto;
    }

    public void transferir(CuentaBancaria destino, double monto) {
        if (destino == null) {
            throw new CuentaNoEncontradaException("La cuenta destino no existe");
        }
        this.retirar(monto);
        destino.depositar(monto);
    }

    public void cerrarCuenta() {
        if (saldo != 0) {
            throw new SaldoNoCeroException("No se puede cerrar la cuenta, el saldo debe ser cero");
        }
        cerrada = true;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isCerrada() {
        return cerrada;
    }
}
