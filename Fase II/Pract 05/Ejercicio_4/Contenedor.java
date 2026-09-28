import java.util.ArrayList;

/**
 * EJERCICIO 4
 * Clase genérica Contenedor<F, S> que almacena múltiples pares en un ArrayList.
 */
public class Contenedor<F, S> {
    private ArrayList<Par<F, S>> pares;

    public Contenedor() {
        pares = new ArrayList<>();
    }

    // agrega un nuevo par al contenedor
    public void agregarPar(F primero, S segundo) {
        pares.add(new Par<>(primero, segundo));
    }

    // devuelve el par en la posición indicada
    public Par<F, S> obtenerPar(int indice) {
        return pares.get(indice);
    }

    // devuelve la lista completa de pares
    public ArrayList<Par<F, S>> obtenerTodosLosPares() {
        return pares;
    }

    // imprime todos los pares almacenados
    public void mostrarPares() {
        for (Par<F, S> par : pares) {
            System.out.println(par);
        }
    }
}
