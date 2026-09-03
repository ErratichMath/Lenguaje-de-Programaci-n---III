import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Experiencia N°1: Single Responsibility Principle (SRP)
 * Se extrae toda la lógica de disponibilidad de Habitacion hacia
 * GestorDisponibilidadHabitacion, dejando a Habitacion con la única
 * responsabilidad de representar los datos de una habitación.
 */
public class Actividad1_SRP {

    // Representación simplificada de una reserva, solo para el cálculo de disponibilidad
    static class Reserva {
        LocalDate checkIn;
        LocalDate checkOut;

        Reserva(LocalDate checkIn, LocalDate checkOut) {
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        boolean seSuperponeCon(LocalDate inicio, LocalDate fin) {
            return !checkOut.isBefore(inicio) && !checkIn.isAfter(fin);
        }
    }

    // b) Nueva clase: única responsabilidad = gestionar disponibilidad
    static class GestorDisponibilidadHabitacion {
        private final List<Reserva> reservas = new ArrayList<>();
        private boolean fueraDeServicio = false;

        boolean estaDisponible(LocalDate inicio, LocalDate fin) {
            if (fueraDeServicio) return false;
            for (Reserva r : reservas) {
                if (r.seSuperponeCon(inicio, fin)) return false;
            }
            return true;
        }

        void registrarReserva(LocalDate inicio, LocalDate fin) {
            reservas.add(new Reserva(inicio, fin));
        }

        void marcarFueraDeServicio() { this.fueraDeServicio = true; }
        void marcarEnServicio() { this.fueraDeServicio = false; }
    }

    // c) Habitacion ya no conoce la lógica de disponibilidad, solo delega
    static class Habitacion {
        private final String numero;
        private final String tipo;
        private final double precioBase;
        private final GestorDisponibilidadHabitacion gestorDisponibilidad;

        Habitacion(String numero, String tipo, double precioBase) {
            this.numero = numero;
            this.tipo = tipo;
            this.precioBase = precioBase;
            this.gestorDisponibilidad = new GestorDisponibilidadHabitacion();
        }

        boolean consultarDisponibilidad(LocalDate inicio, LocalDate fin) {
            return gestorDisponibilidad.estaDisponible(inicio, fin);
        }

        void reservar(LocalDate inicio, LocalDate fin) {
            gestorDisponibilidad.registrarReserva(inicio, fin);
        }

        String getNumero() { return numero; }
        String getTipo() { return tipo; }
        double getPrecioBase() { return precioBase; }
    }

    // d) Controlador que usa el gestor a través de Habitacion
    static class ControladorReservas {
        void crearReserva(Habitacion habitacion, LocalDate inicio, LocalDate fin) {
            if (habitacion.consultarDisponibilidad(inicio, fin)) {
                habitacion.reservar(inicio, fin);
                System.out.println("Reserva creada para la habitación " + habitacion.getNumero());
            } else {
                System.out.println("Habitación " + habitacion.getNumero() + " no disponible en esas fechas.");
            }
        }
    }

    public static void main(String[] args) {
        Habitacion h101 = new Habitacion("101", "Doble", 150.0);
        ControladorReservas controlador = new ControladorReservas();

        controlador.crearReserva(h101, LocalDate.of(2026, 9, 10), LocalDate.of(2026, 9, 15));
        // Se superpone con la anterior: debe rechazarse
        controlador.crearReserva(h101, LocalDate.of(2026, 9, 12), LocalDate.of(2026, 9, 14));
        // No se superpone: debe aceptarse
        controlador.crearReserva(h101, LocalDate.of(2026, 9, 20), LocalDate.of(2026, 9, 22));
    }
}
