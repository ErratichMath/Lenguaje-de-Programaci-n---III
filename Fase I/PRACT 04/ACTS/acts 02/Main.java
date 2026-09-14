public class Main {
    public static void main(String[] args) {
        CuentaBancaria origen = new CuentaBancaria("001", "Juan Perez", 500.0);
        CuentaBancaria destino = new CuentaBancaria("002", "Maria Lopez", 100.0);

        System.out.println("=== Prueba: transferencias ===");
        try {
            origen.transferir(destino, 200.0);
            System.out.println("Transferencia exitosa. Saldo origen: " + origen.getSaldo() + ", saldo destino: " + destino.getSaldo());
        } catch (SaldoInsuficienteException | CuentaNoEncontradaException e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        try {
            origen.transferir(null, 50.0);
        } catch (CuentaNoEncontradaException e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        try {
            origen.transferir(destino, 10000.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: cierre de cuentas ===");
        CuentaBancaria cuenta3 = new CuentaBancaria("003", "Carlos Ruiz", 0.0);
        try {
            cuenta3.cerrarCuenta();
            System.out.println("Cuenta " + cuenta3.getNumeroCuenta() + " cerrada correctamente");
        } catch (SaldoNoCeroException e) {
            System.out.println("Error al cerrar cuenta: " + e.getMessage());
        }

        try {
            origen.cerrarCuenta();
        } catch (SaldoNoCeroException e) {
            System.out.println("Error al cerrar cuenta: " + e.getMessage());
        }
    }
}
