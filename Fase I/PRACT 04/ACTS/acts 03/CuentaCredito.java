public class CuentaCredito extends CuentaBancaria {
    private double limiteCredito;

    public CuentaCredito(String numeroCuenta, String titular, double saldoInicial, double limiteCredito) {
        super(numeroCuenta, titular, saldoInicial);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        double disponible = saldo + limiteCredito;
        if (monto > disponible) {
            throw new LimiteCreditoExcedidoException("El retiro supera el limite de credito disponible");
        }
        saldo -= monto;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }
}
