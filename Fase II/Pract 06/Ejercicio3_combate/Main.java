import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CombateView vista = new CombateView();

        String nombre = vista.solicitar("Nombre del jugador: ");
        Jugador jugador = new Jugador(nombre.isEmpty() ? "Heroe" : nombre, 100, 1);

        // Inventario inicial
        jugador.getInventario().agregarItem(new Item("Espada", 1, TipoItem.ARMA, "Espada de hierro", 8));
        jugador.getInventario().agregarItem(new Item("Daga", 1, TipoItem.ARMA, "Daga ligera", 5));
        jugador.getInventario().agregarItem(new Item("Pocion de vida", 3, TipoItem.POCION, "Restaura 20 de salud", 20));

        // Enemigos
        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Goblin", 30, 1, "Goblin"));
        enemigos.add(new Enemigo("Orco", 50, 2, "Bestia"));

        CombateModel modelo = new CombateModel(jugador, enemigos);
        new CombateController(modelo, vista).iniciar();
    }
}
