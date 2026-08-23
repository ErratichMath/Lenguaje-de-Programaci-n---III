package ACT05;

public class CuentaCorriente extends Cuenta {
    private int retiros;

    // Constructor de una cuenta corriente con saldo 0
    public CuentaCorriente(int numeroCuenta) {
        super(numeroCuenta, 0);
        this.retiros = 0;
    }

    @Override
    public void retirar(double monto) {
        final int LIBRE_RETIROS = 3;
        final double TARIFA_TRANSACCION = 3.0;

        super.retirar(monto);
        retiros++;
        if (retiros > LIBRE_RETIROS) {
            super.retirar(TARIFA_TRANSACCION);
        }
    }

    @Override
    public void consultar() {
        // Se restablece el contador de retiros para el siguiente mes
        retiros = 0;
    }
}