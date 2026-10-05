import java.util.List;
import java.util.Scanner;

/** VISTA: solo muestra datos y lee la entrada del usuario. */
public class CarritoVista {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n===== TIENDA =====");
        System.out.println("1. Agregar producto al catálogo");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Eliminar producto del carrito");
        System.out.println("6. Aplicar descuento");
        System.out.println("7. Calcular envío");
        System.out.println("8. Ver historial de compras");
        System.out.println("9. Realizar compra");
        System.out.println("0. Salir");
    }

    public String solicitar(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("\n--- Catálogo ---");
        if (productos.isEmpty()) System.out.println("No hay productos.");
        for (Producto p : productos) System.out.println(p);
    }

    public void mostrarCarrito(List<LineaCarrito> lineas, double subtotal, double descuento,
                               double envio, double total) {
        System.out.println("\n--- Carrito ---");
        if (lineas.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        for (LineaCarrito l : lineas) {
            System.out.println("#" + l.getProducto().getId() + " " + l);
        }
        System.out.println(String.format("Subtotal: S/ %.2f", subtotal));
        System.out.println(String.format("Descuento: -S/ %.2f", descuento));
        System.out.println(String.format("Envío: S/ %.2f", envio));
        System.out.println(String.format("TOTAL: S/ %.2f", total));
    }

    public void mostrarHistorial(List<Compra> compras) {
        System.out.println("\n--- Historial de compras ---");
        if (compras.isEmpty()) System.out.println("Aún no hay compras.");
        for (Compra c : compras) System.out.println(c + "\n");
    }

    public void cerrarScanner() { scanner.close(); }
}
