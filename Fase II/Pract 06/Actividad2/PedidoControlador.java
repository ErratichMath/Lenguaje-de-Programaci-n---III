/** CONTROLADOR: recibe las opciones de la vista, usa el modelo y pide a la vista mostrar el resultado. */
public class PedidoControlador {
    private final PedidoModelo modelo;
    private final PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitar("Selecciona una opción: ");
            switch (opcion) {
                case "1": agregarPedido(); break;
                case "2": vista.mostrarPedidos("Pedidos actuales", modelo.getPedidos()); break;
                case "3": eliminarPedido(); break;
                case "4": actualizarPedido(); break;
                case "5": buscarPedido(); break;
                case "6": vista.mostrarConteo(modelo.contarTotal(), modelo.contarPorTipo()); break;
                case "0": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }

    private void agregarPedido() {
        String nombre = vista.solicitar("Nombre del plato: ");
        String tipo = vista.solicitar("Tipo (Entrada/Fondo/Postre/Bebida): ");
        if (nombre.isEmpty() || tipo.isEmpty()) {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacíos.");
            return;
        }
        modelo.agregarPedido(new Pedido(nombre, tipo));
        vista.mostrarMensaje("Pedido agregado: " + nombre + " (" + tipo + ")");
    }

    private void eliminarPedido() {
        Integer id = pedirId();
        if (id == null) return;
        vista.mostrarMensaje(modelo.eliminarPedido(id) ? "Pedido eliminado." : "No existe un pedido con ese id.");
    }

    private void actualizarPedido() {
        Integer id = pedirId();
        if (id == null) return;
        String nuevo = vista.solicitar("Nuevo nombre: ");
        if (nuevo.isEmpty()) {
            vista.mostrarMensaje("El nombre no puede estar vacío.");
            return;
        }
        vista.mostrarMensaje(modelo.actualizarPedido(id, nuevo) ? "Pedido actualizado." : "No existe un pedido con ese id.");
    }

    private void buscarPedido() {
        String texto = vista.solicitar("Buscar por nombre o tipo: ");
        vista.mostrarPedidos("Resultados", modelo.buscar(texto));
    }

    /** Pide un id numérico; devuelve null si el dato no es válido. */
    private Integer pedirId() {
        try {
            return Integer.parseInt(vista.solicitar("Id del pedido: "));
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Id inválido.");
            return null;
        }
    }
}
