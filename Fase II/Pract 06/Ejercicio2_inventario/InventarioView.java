import java.util.List;
import java.util.Scanner;

/** VISTA: muestra información y captura la entrada del usuario. */
public class InventarioView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n===== INVENTARIO =====");
        System.out.println("1. Agregar ítem");
        System.out.println("2. Ver inventario");
        System.out.println("3. Eliminar ítem");
        System.out.println("4. Buscar ítem");
        System.out.println("5. Ver detalles de un ítem");
        System.out.println("6. Usar ítem");
        System.out.println("0. Salir");
    }

    public String solicitar(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarInventario(List<Item> items) {
        System.out.println("\n--- Inventario ---");
        if (items.isEmpty()) System.out.println("El inventario está vacío.");
        for (Item i : items) System.out.println("- " + i);
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    public void mostrarDetallesItem(Item item) {
        System.out.println("\nNombre: " + item.getNombre());
        System.out.println("Cantidad: " + item.getCantidad());
        System.out.println("Tipo: " + item.getTipo().getEtiqueta());
        System.out.println("Descripción: " + item.getDescripcion());
        System.out.println("Poder: " + item.getPoder());
    }

    public void cerrarScanner() { scanner.close(); }
}
