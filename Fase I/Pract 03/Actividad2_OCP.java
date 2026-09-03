import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Experiencia N°2: Open/Closed Principle (OCP)
 * Reserva delega la lógica de cancelación en una abstracción (PoliticaCancelacion),
 * de modo que añadir nuevas políticas no exige modificar Reserva.
 */
public class Actividad2_OCP {

    interface PoliticaCancelacion {
        boolean puedeCancelar(Reserva reserva, LocalDateTime ahora);
        double calcularPenalizacion(Reserva reserva, LocalDateTime ahora);
    }

    static class PoliticaCancelacionFlexible implements PoliticaCancelacion {
        public boolean puedeCancelar(Reserva reserva, LocalDateTime ahora) {
            return ChronoUnit.HOURS.between(ahora, reserva.fechaCheckIn) >= 24;
        }
        public double calcularPenalizacion(Reserva reserva, LocalDateTime ahora) { return 0.0; }
    }

    static class PoliticaCancelacionModerada implements PoliticaCancelacion {
        public boolean puedeCancelar(Reserva reserva, LocalDateTime ahora) {
            return ChronoUnit.HOURS.between(ahora, reserva.fechaCheckIn) >= 72;
        }
        public double calcularPenalizacion(Reserva reserva, LocalDateTime ahora) {
            return reserva.monto * 0.5;
        }
    }

    static class PoliticaCancelacionEstricta implements PoliticaCancelacion {
        public boolean puedeCancelar(Reserva reserva, LocalDateTime ahora) { return false; }
        public double calcularPenalizacion(Reserva reserva, LocalDateTime ahora) { return reserva.monto; }
    }

    // Si en el futuro se necesita, por ejemplo, PoliticaCancelacionConCreditoFuturo,
    // basta con crear una nueva clase que implemente PoliticaCancelacion: Reserva NO se toca.

    static class Reserva {
        String codigo;
        LocalDateTime fechaCheckIn;
        double monto;
        PoliticaCancelacion politicaCancelacion;

        Reserva(String codigo, LocalDateTime fechaCheckIn, double monto, PoliticaCancelacion politica) {
            this.codigo = codigo;
            this.fechaCheckIn = fechaCheckIn;
            this.monto = monto;
            this.politicaCancelacion = politica;
        }

        void cancelar(LocalDateTime ahora) {
            if (politicaCancelacion.puedeCancelar(this, ahora)) {
                double penalizacion = politicaCancelacion.calcularPenalizacion(this, ahora);
                System.out.printf("Reserva %s cancelada. Penalización: %.2f%n", codigo, penalizacion);
            } else {
                System.out.println("Reserva " + codigo + " no puede cancelarse según su política.");
            }
        }
    }

    public static void main(String[] args) {
        LocalDateTime ahora = LocalDateTime.of(2026, 9, 2, 10, 0);

        Reserva r1 = new Reserva("R1", ahora.plusHours(30), 300.0, new PoliticaCancelacionFlexible());
        Reserva r2 = new Reserva("R2", ahora.plusHours(40), 300.0, new PoliticaCancelacionModerada());
        Reserva r3 = new Reserva("R3", ahora.plusHours(10), 300.0, new PoliticaCancelacionEstricta());

        r1.cancelar(ahora);
        r2.cancelar(ahora);
        r3.cancelar(ahora);
    }
}
