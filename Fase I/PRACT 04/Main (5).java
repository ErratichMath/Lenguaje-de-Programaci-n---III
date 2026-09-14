public class Main {
    public static void main(String[] args) {
        CuentaCredito cuentaCredito = new CuentaCredito("001", "Juan Perez", 100.0, 300.0);
        CuentaBancaria cuentaNormal = new CuentaBancaria("002", "Maria Lopez", 50.0);

        System.out.println("=== Prueba: retiro dentro del limite de credito ===");
        try {
            cuentaCredito.retirar(300.0);
            System.out.println("Retiro exitoso, saldo actual: " + cuentaCredito.getSaldo());
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: retiro que excede el limite de credito ===");
        try {
            cuentaCredito.retirar(500.0);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: transferencia usando credito ===");
        try {
            cuentaCredito.transferir(cuentaNormal, 200.0);
            System.out.println("Transferencia exitosa, saldo cuentaCredito: " + cuentaCredito.getSaldo() + ", saldo cuentaNormal: " + cuentaNormal.getSaldo());
        } catch (LimiteCreditoExcedidoException | SaldoInsuficienteException | CuentaNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: cuenta sin limite de credito ===");
        try {
            cuentaNormal.retirar(1000.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
