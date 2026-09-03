/**
 * Experiencia N°3: Liskov Substitution Principle (LSP)
 * Todas las subclases de Habitacion cumplen el mismo contrato del método
 * calcularPrecioFinal(): siempre devuelve un valor >= 0 y nunca lanza excepciones,
 * por lo que el controlador puede tratarlas de forma uniforme (polimorfismo).
 */
public class Actividad3_LSP {

    static abstract class Habitacion {
        protected String numero;
        protected double precioBase;

        Habitacion(String numero, double precioBase) {
            this.numero = numero;
            this.precioBase = precioBase;
        }

        // Contrato: nunca lanza excepción, siempre retorna un valor >= 0
        abstract double calcularPrecioFinal(int nochesEstadia);

        String getNumero() { return numero; }
    }

    static class HabitacionIndividual extends Habitacion {
        HabitacionIndividual(String numero, double precioBase) { super(numero, precioBase); }
        @Override
        double calcularPrecioFinal(int nochesEstadia) { return precioBase * nochesEstadia; }
    }

    static class HabitacionSuite extends Habitacion {
        private final double recargoServicios;
        HabitacionSuite(String numero, double precioBase, double recargoServicios) {
            super(numero, precioBase);
            this.recargoServicios = recargoServicios;
        }
        @Override
        double calcularPrecioFinal(int nochesEstadia) {
            return (precioBase + recargoServicios) * nochesEstadia;
        }
    }

    // Antes de la corrección, una hipotética HabitacionFueraDeServicio lanzaba una
    // excepción en calcularPrecioFinal(), rompiendo el contrato de la clase base y
    // violando LSP: el controlador ya no podía tratar todas las habitaciones igual.
    // Solución: toda habitación sabe calcular su propio precio (aunque sea 0);
    // la disponibilidad se controla aparte (ver Actividad 1), no lanzando excepciones
    // desde un método que el contrato promete no romper.
    static class HabitacionFueraDeServicio extends Habitacion {
        HabitacionFueraDeServicio(String numero) { super(numero, 0); }
        @Override
        double calcularPrecioFinal(int nochesEstadia) { return 0.0; }
    }

    static class ControladorHabitaciones {
        void mostrarPrecio(Habitacion habitacion, int noches) {
            // Funciona igual sin importar el tipo concreto de Habitacion (LSP cumplido)
            System.out.printf("Habitación %s -> Precio por %d noches: %.2f%n",
                    habitacion.getNumero(), noches, habitacion.calcularPrecioFinal(noches));
        }
    }

    public static void main(String[] args) {
        ControladorHabitaciones controlador = new ControladorHabitaciones();
        Habitacion[] habitaciones = {
                new HabitacionIndividual("101", 100),
                new HabitacionSuite("201", 200, 50),
                new HabitacionFueraDeServicio("305")
        };
        for (Habitacion h : habitaciones) controlador.mostrarPrecio(h, 3);
    }
}
