import java.util.List;
import java.util.Scanner;

/** VISTA: muestra el estado del combate y los mensajes de lo que sucede. */
public class CombateView {
    private final Scanner scanner = new Scanner(System.in);

    public String solicitar(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    public void mostrarEstado(Jugador jugador, List<Enemigo> enemigos, int turno) {
        System.out.println("\n========== TURNO " + turno + " ==========");
        String arma = (jugador.getArmaEquipada() == null) ? "ninguna (puños)" : jugador.getArmaEquipada().getNombre();
        System.out.println(jugador.getNombre() + " (Nv " + jugador.getNivel() + ") - Salud: "
                + jugador.getSalud() + "/" + jugador.getSaludMaxima() + " - Arma: " + arma);
        System.out.println("Enemigos:");
        for (Enemigo e : enemigos) {
            String estado = e.estaVivo() ? e.getSalud() + "/" + e.getSaludMaxima() : "derrotado";
            System.out.println("  - " + e.getNombre() + " [" + e.getTipo() + ", Nv " + e.getNivel() + "] Salud: " + estado);
        }
    }

    public void mostrarMenuCombate() {
        System.out.println("\nAcciones:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar objeto");
        System.out.println("3. Ver inventario");
        System.out.println("0. Huir");
    }

    /** Lista numerada de enemigos vivos para elegir objetivo. */
    public void mostrarObjetivos(List<Enemigo> vivos) {
        for (int i = 0; i < vivos.size(); i++) {
            Enemigo e = vivos.get(i);
            System.out.println((i + 1) + ". " + e.getNombre() + " (" + e.getSalud() + " de salud)");
        }
    }

    public void mostrarInventario(List<Item> items) {
        System.out.println("\n--- Inventario ---");
        if (items.isEmpty()) System.out.println("Vacío.");
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            System.out.println((i + 1) + ". " + it + " (poder " + it.getPoder() + ") - " + it.getDescripcion());
        }
    }

    public void cerrarScanner() { scanner.close(); }
}
