/**
 * Experiencia N°4: Interface Segregation Principle (ISP)
 * En vez de una interfaz "ServicioHabitacion" con todos los métodos,
 * se dividen en interfaces pequeñas y específicas.
 */
public class Actividad4_ISP {

    interface ServicioLimpieza {
        void solicitarLimpieza();
    }

    interface ServicioComida {
        void solicitarComida(String pedido);
    }

    interface ServicioLavanderia {
        void solicitarLavanderia();
    }

    // Solo implementa lo que necesita
    static class HabitacionEstandar implements ServicioLimpieza {
        public void solicitarLimpieza() {
            System.out.println("Limpieza solicitada para habitación estándar.");
        }
    }

    // Implementa varias interfaces pequeñas según lo que ofrece
    static class HabitacionSuite implements ServicioLimpieza, ServicioComida, ServicioLavanderia {
        public void solicitarLimpieza() {
            System.out.println("Limpieza premium solicitada para suite.");
        }
        public void solicitarComida(String pedido) {
            System.out.println("Suite: pedido de comida -> " + pedido);
        }
        public void solicitarLavanderia() {
            System.out.println("Suite: servicio de lavandería solicitado.");
        }
    }

    static class ControladorServicios {
        void gestionarSolicitud(Object habitacion) {
            if (habitacion instanceof ServicioLimpieza) {
                ((ServicioLimpieza) habitacion).solicitarLimpieza();
            }
            if (habitacion instanceof ServicioComida) {
                ((ServicioComida) habitacion).solicitarComida("Desayuno continental");
            }
            if (habitacion instanceof ServicioLavanderia) {
                ((ServicioLavanderia) habitacion).solicitarLavanderia();
            }
        }
    }

    public static void main(String[] args) {
        ControladorServicios controlador = new ControladorServicios();
        controlador.gestionarSolicitud(new HabitacionEstandar());
        controlador.gestionarSolicitud(new HabitacionSuite());
    }
}
