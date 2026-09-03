/**
 * Ejercicio Propuesto 3: LSP
 *
 * ANÁLISIS:
 * El método acelerar() no debe prometer CÓMO se acelera (motor o pedaleo),
 * sino únicamente QUÉ efecto produce: incrementar la velocidad del vehículo
 * sin lanzar excepciones. Si el contrato de Vehiculo se limita a esa
 * postcondición, tanto Coche como Bicicleta la cumplen, aunque el mecanismo
 * interno sea distinto, por lo que SÍ respetan LSP.
 * El problema aparecería solo si algún cliente asumiera detalles internos no
 * declarados en el contrato (p. ej. "todo vehículo consume combustible al
 * acelerar"); eso sería un error de diseño del cliente, no de las subclases.
 * Por eso, a continuación se deja explícito el contrato en la clase base y se
 * valida mediante el controlador que ambas subclases lo cumplen.
 */
public class Ejercicio3_LSP {

    static abstract class Vehiculo {
        protected double velocidadActual;

        // Contrato: incrementa velocidadActual y jamás lanza una excepción
        abstract void acelerar();

        double getVelocidadActual() { return velocidadActual; }
    }

    static class Coche extends Vehiculo {
        @Override
        void acelerar() {
            velocidadActual += 20;
            System.out.println("El coche acelera usando el motor. Velocidad: " + velocidadActual);
        }
    }

    static class Bicicleta extends Vehiculo {
        @Override
        void acelerar() {
            velocidadActual += 5;
            System.out.println("La bicicleta acelera pedaleando. Velocidad: " + velocidadActual);
        }
    }

    static class ControladorVehiculos {
        void probarAceleracion(Vehiculo vehiculo) {
            double antes = vehiculo.getVelocidadActual();
            vehiculo.acelerar();
            if (vehiculo.getVelocidadActual() <= antes) {
                throw new IllegalStateException("Violación de LSP: no se incrementó la velocidad.");
            }
        }
    }

    public static void main(String[] args) {
        ControladorVehiculos controlador = new ControladorVehiculos();
        Vehiculo[] vehiculos = { new Coche(), new Bicicleta() };
        for (Vehiculo v : vehiculos) controlador.probarAceleracion(v);
        System.out.println("Ambas subclases cumplen el contrato de Vehiculo: LSP respetado.");
    }
}
