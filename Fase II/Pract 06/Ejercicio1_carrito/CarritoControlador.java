/** CONTROLADOR: interpreta las opciones del usuario y coordina modelo y vista. */
public class CarritoControlador {
    private final CarritoModelo modelo;
    private final CarritoVista vista;

    public CarritoControlador(CarritoModelo modelo, CarritoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitar("Selecciona una opción: ");
            switch (opcion) {
                case "1": agregarProducto(); break;
                case "2": vista.mostrarProductos(modelo.getCatalogo()); break;
                case "3": agregarAlCarrito(); break;
                case "4": verCarrito(); break;
                case "5": eliminarDelCarrito(); break;
                case "6": aplicarDescuento(); break;
                case "7": calcularEnvio(); break;
                case "8": vista.mostrarHistorial(modelo.getHistorial()); break;
                case "9": realizarCompra(); break;
                case "0": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida.");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }

    private void agregarProducto() {
        String nombre = vista.solicitar("Nombre del producto: ");
        if (nombre.isEmpty()) { vista.mostrarMensaje("El nombre no puede estar vacío."); return; }
        Double precio = pedirDecimal("Precio: ");
        if (precio == null) return;
        Integer stock = pedirEntero("Stock: ");
        if (stock == null) return;
        if (precio <= 0 || stock < 0) {
            vista.mostrarMensaje("Precio o stock inválido.");
            return;
        }
        modelo.agregarProducto(nombre, precio, stock);
        vista.mostrarMensaje("Producto agregado: " + nombre);
    }

    private void agregarAlCarrito() {
        vista.mostrarProductos(modelo.getCatalogo());
        Integer id = pedirEntero("Id del producto: ");
        if (id == null) return;
        Integer cantidad = pedirEntero("Cantidad: ");
        if (cantidad == null) return;
        String error = modelo.agregarAlCarrito(id, cantidad);
        vista.mostrarMensaje(error == null ? "Producto agregado al carrito." : error);
    }

    private void verCarrito() {
        vista.mostrarCarrito(modelo.getCarrito(), modelo.getSubtotal(),
                modelo.getMontoDescuento(), modelo.calcularEnvio(), modelo.getTotal());
    }

    private void eliminarDelCarrito() {
        Integer id = pedirEntero("Id del producto a quitar: ");
        if (id == null) return;
        vista.mostrarMensaje(modelo.eliminarDelCarrito(id)
                ? "Producto eliminado del carrito."
                : "Ese producto no está en el carrito.");
    }

    private void aplicarDescuento() {
        String codigo = vista.solicitar("Código de descuento (DESC10 / DESC20): ");
        vista.mostrarMensaje(modelo.aplicarDescuento(codigo)
                ? "Descuento aplicado."
                : "Código inválido.");
    }

    private void calcularEnvio() {
        vista.mostrarMensaje(String.format("Costo de envío: S/ %.2f (gratis desde S/ 100 tras descuento)",
                modelo.calcularEnvio()));
    }

    private void realizarCompra() {
        Compra compra = modelo.realizarCompra();
        vista.mostrarMensaje(compra == null
                ? "El carrito está vacío."
                : "¡Compra realizada!\n" + compra);
    }

    // ---------- Utilidades de entrada ----------
    private Integer pedirEntero(String mensaje) {
        try {
            return Integer.parseInt(vista.solicitar(mensaje));
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Valor numérico inválido.");
            return null;
        }
    }

    private Double pedirDecimal(String mensaje) {
        try {
            return Double.parseDouble(vista.solicitar(mensaje).replace(',', '.'));
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Valor numérico inválido.");
            return null;
        }
    }
}
