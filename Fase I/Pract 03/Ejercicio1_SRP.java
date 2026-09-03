/**
 * Ejercicio Propuesto 1: SRP
 * Empleado solo almacena datos; CalculadoraPago asume la responsabilidad
 * de calcular el pago mensual.
 */
public class Ejercicio1_SRP {

    static class Empleado {
        private final String nombre;
        private final double salario;
        private final String departamento;

        Empleado(String nombre, double salario, String departamento) {
            this.nombre = nombre;
            this.salario = salario;
            this.departamento = departamento;
        }

        String getNombre() { return nombre; }
        double getSalario() { return salario; }
        String getDepartamento() { return departamento; }
    }

    static class CalculadoraPago {
        double calcularPagoMensual(Empleado empleado) {
            double bono = empleado.getDepartamento().equalsIgnoreCase("Ventas") ? 200 : 0;
            return empleado.getSalario() + bono;
        }
    }

    public static void main(String[] args) {
        Empleado e1 = new Empleado("Ana Torres", 2500, "Ventas");
        Empleado e2 = new Empleado("Luis Paredes", 2200, "Sistemas");

        CalculadoraPago calculadora = new CalculadoraPago();
        System.out.printf("Pago mensual de %s: %.2f%n", e1.getNombre(), calculadora.calcularPagoMensual(e1));
        System.out.printf("Pago mensual de %s: %.2f%n", e2.getNombre(), calculadora.calcularPagoMensual(e2));
    }
}
