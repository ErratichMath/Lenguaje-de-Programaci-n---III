/*
 * EJERCICIO 2: Calculadora Basica
 * --------------------------------
 * Suma, resta, multiplicacion y division. dividir() lanza
 * DivisionPorCeroException (personalizada) si el divisor es 0.
 * En el main se capturan tanto IllegalArgumentException como
 * ArithmeticException con catch multiples (usamos catch (A | B) ...).
 */

class DivisionPorCeroException extends ArithmeticException {
    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}

class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new DivisionPorCeroException(
                    "No se puede dividir " + a + " entre cero");
        }
        return a / b;
    }
}

public class Ejercicio2 {

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        double[][] operaciones = {
                {10, 2},
                {5, 0},
                {8, 4}
        };

        for (double[] op : operaciones) {
            double a = op[0];
            double b = op[1];
            try {
                System.out.println(a + " + " + b + " = " + calc.sumar(a, b));
                System.out.println(a + " - " + b + " = " + calc.restar(a, b));
                System.out.println(a + " * " + b + " = " + calc.multiplicar(a, b));
                System.out.println(a + " / " + b + " = " + calc.dividir(a, b));
            } catch (IllegalArgumentException | ArithmeticException e) {
                // DivisionPorCeroException extiende ArithmeticException,
                // por eso cae en este catch multiple
                System.out.println("Error en la operacion con " + a + " y " + b
                        + ": " + e.getMessage());
            }
            System.out.println("---");
        }
    }
}
