/**
 * Experiencia N°5: Dependency Inversion Principle (DIP)
 * NotificadorReserva depende de la abstracción CanalNotificacion, no de una
 * implementación concreta. El canal se inyecta por constructor.
 */
public class Actividad5_DIP {

    interface CanalNotificacion {
        void enviarNotificacion(String mensaje);
    }

    static class EnviadorCorreo implements CanalNotificacion {
        public void enviarNotificacion(String mensaje) {
            System.out.println("[Correo] " + mensaje);
        }
    }

    static class EnviadorSMS implements CanalNotificacion {
        public void enviarNotificacion(String mensaje) {
            System.out.println("[SMS] " + mensaje);
        }
    }

    static class NotificadorSlack implements CanalNotificacion {
        public void enviarNotificacion(String mensaje) {
            System.out.println("[Slack] " + mensaje);
        }
    }

    static class NotificadorReserva {
        private final CanalNotificacion canal; // depende de la abstracción

        NotificadorReserva(CanalNotificacion canal) {
            this.canal = canal;
        }

        void notificarConfirmacion(String codigoReserva) {
            canal.enviarNotificacion("Su reserva " + codigoReserva + " ha sido confirmada.");
        }
    }

    public static void main(String[] args) {
        new NotificadorReserva(new EnviadorCorreo()).notificarConfirmacion("R-001");
        new NotificadorReserva(new EnviadorSMS()).notificarConfirmacion("R-002");
        new NotificadorReserva(new NotificadorSlack()).notificarConfirmacion("R-003");
        // Añadir un nuevo canal (ej. WhatsApp) solo requiere una nueva clase
        // que implemente CanalNotificacion; NotificadorReserva no cambia.
    }
}
