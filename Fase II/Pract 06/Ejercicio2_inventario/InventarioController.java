/** CONTROLADOR: recibe las acciones del usuario, usa el modelo y le pide a la vista que muestre el resultado. */
public class InventarioController {
    private final InventarioModel modelo;
    private final InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitar("Selecciona una opción: ");
            switch (opcion) {
                case "1": agregarItem(); break;
                case "2": verInventario(); break;
                case "3": eliminarItem(); break;
                case "4": buscarItem(); break;
                case "5": mostrarDetalles(); break;
                case "6": usarItem(); break;
                case "0": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida.");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }

    public void agregarItem() {
        String nombre = vista.solicitar("Nombre: ");
        if (nombre.isEmpty()) { vista.mostrarMensaje("El nombre no puede estar vacío."); return; }
        try {
            int cantidad = Integer.parseInt(vista.solicitar("Cantidad: "));
            String t = vista.solicitar("Tipo (1. Arma  2. Poción): ");
            TipoItem tipo = t.equals("1") ? TipoItem.ARMA : TipoItem.POCION;
            String descripcion = vista.solicitar("Descripción: ");
            int poder = Integer.parseInt(vista.solicitar("Poder (daño o curación): "));
            if (cantidad <= 0 || poder < 0) {
                vista.mostrarMensaje("Cantidad o poder inválido.");
                return;
            }
            modelo.agregarItem(new Item(nombre, cantidad, tipo, descripcion, poder));
            vista.mostrarMensaje("Ítem agregado: " + nombre);
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Valor numérico inválido.");
        }
    }

    public void verInventario() { vista.mostrarInventario(modelo.obtenerItems()); }

    public void eliminarItem() {
        Item item = modelo.buscarItem(vista.solicitar("Nombre del ítem a eliminar: "));
        if (item == null) vista.mostrarMensaje("No se encontró el ítem.");
        else {
            modelo.eliminarItem(item);
            vista.mostrarMensaje("Ítem eliminado: " + item.getNombre());
        }
    }

    public void buscarItem() {
        Item item = modelo.buscarItem(vista.solicitar("Nombre a buscar: "));
        vista.mostrarMensaje(item == null ? "No se encontró el ítem." : "Encontrado: " + item);
    }

    public void mostrarDetalles() {
        Item item = modelo.buscarItem(vista.solicitar("Nombre del ítem: "));
        if (item == null) vista.mostrarMensaje("No se encontró el ítem.");
        else vista.mostrarDetallesItem(item);
    }

    public void usarItem() {
        Item item = modelo.buscarItem(vista.solicitar("Nombre del ítem a usar: "));
        if (item == null) { vista.mostrarMensaje("No se encontró el ítem."); return; }
        if (!item.usarItem()) { vista.mostrarMensaje("No quedan unidades."); return; }
        vista.mostrarMensaje("Usaste " + item.getNombre() + ".");
        if (item.getCantidad() == 0) {            // si se agotó, se quita del inventario
            modelo.eliminarItem(item);
            vista.mostrarMensaje(item.getNombre() + " se agotó.");
        }
    }
}
