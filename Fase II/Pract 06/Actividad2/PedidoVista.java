import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** VISTA: solo muestra información y captura la entrada del usuario. */
public class PedidoVista {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n===== RESTAURANTE =====");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Mostrar pedidos");
        System.out.println("3. Eliminar pedido");
        System.out.println("4. Actualizar pedido");
        System.out.println("5. Buscar pedido (nombre o tipo)");
        System.out.println("6. Contar pedidos (total y por tipo)");
        System.out.println("0. Salir");
    }

    public String solicitar(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    public void mostrarPedidos(String titulo, List<Pedido> pedidos) {
        System.out.println("\n--- " + titulo + " ---");
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos para mostrar.");
        } else {
            for (Pedido p : pedidos) System.out.println(p);
        }
    }

    public void mostrarConteo(int total, Map<String, Integer> porTipo) {
        System.out.println("\nTotal de pedidos: " + total);
        for (Map.Entry<String, Integer> e : porTipo.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue());
        }
    }

    public void cerrarScanner() { scanner.close(); }
}
