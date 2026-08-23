package ACT05;
import java.util.Scanner;

public class AppBanco {
    public static void main(String[] args) {

        // Creamos 5 cuentas corrientes y 5 cuentas de ahorro (polimorfismo: arreglo de Cuenta)
        Cuenta[] cuentas = new Cuenta[10];
        for (int i = 0; i < 5; i++) {
            cuentas[i] = new CuentaCorriente(1000 + i);
        }
        for (int i = 5; i < 10; i++) {
            CuentaAhorro ca = new CuentaAhorro(2000 + i);
            ca.setTasaInteres(1.5);
            cuentas[i] = ca;
        }

        Scanner in = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            System.out.print("\nD)epositar  R)etirar  C)onsultar  S)alir: ");
            String op = in.next();

            if (op.equalsIgnoreCase("D") || op.equalsIgnoreCase("R")) {
                System.out.print("Ingrese un número de cuenta (0-9) y un monto: ");
                int num = in.nextInt();
                double monto = in.nextDouble();

                if (num < 0 || num >= cuentas.length) {
                    System.out.println("Número de cuenta inválido.");
                    continue;
                }

                if (op.equalsIgnoreCase("D")) {
                    cuentas[num].depositar(monto);
                } else {
                    cuentas[num].retirar(monto);
                }
                System.out.println("Saldo: " + cuentas[num].getSaldo());

            } else if (op.equalsIgnoreCase("C")) {
                for (int n = 0; n < cuentas.length; n++) {
                    cuentas[n].consultar();
                    System.out.println(n + " (" + cuentas[n].getClass().getSimpleName()
                            + ") -> Saldo: " + cuentas[n].getSaldo());
                }

            } else if (op.equalsIgnoreCase("S")) {
                done = true;
            } else {
                System.out.println("Opción no válida.");
            }
        }

        in.close();
        System.out.println("¡Hasta luego!");
    }
}