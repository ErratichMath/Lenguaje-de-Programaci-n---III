public class Main {
    public static void main(String[] args) {
        System.out.println("=== Prueba: creacion de cuentas ===");
        try {
            CuentaBancaria cuenta1 = new CuentaBancaria("001", "Juan Perez", 500.0);
            System.out.println("Cuenta creada: " + cuenta1.getNumeroCuenta() + " - Saldo: " + cuenta1.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            CuentaBancaria cuentaInvalida = new CuentaBancaria("002", "Maria Lopez", -100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear cuenta: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: depositos y retiros ===");
        CuentaBancaria cuenta2 = new CuentaBancaria("003", "Carlos Ruiz", 200.0);

        try {
            cuenta2.depositar(100.0);
            System.out.println("Deposito exitoso, saldo actual: " + cuenta2.getSaldo());
            cuenta2.depositar(-50.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error en deposito: " + e.getMessage());
        }

        try {
            cuenta2.retirar(1000.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error en retiro: " + e.getMessage());
        }

        try {
            cuenta2.retirar(50.0);
            System.out.println("Retiro exitoso, saldo actual: " + cuenta2.getSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error en retiro: " + e.getMessage());
        }
    }
}
