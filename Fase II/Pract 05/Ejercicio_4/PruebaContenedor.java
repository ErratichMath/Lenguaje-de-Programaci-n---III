/**
 * EJERCICIO 4 - Prueba de la clase Contenedor.
 */
public class PruebaContenedor {
    public static void main(String[] args) {
        Contenedor<String, Integer> contenedor = new Contenedor<>();

        contenedor.agregarPar("Manzana", 10);
        contenedor.agregarPar("Banana", 25);
        contenedor.agregarPar("Naranja", 7);

        System.out.println("Todos los pares:");
        contenedor.mostrarPares();

        System.out.println("\nEl par en la posicion 1 es: " + contenedor.obtenerPar(1));
        System.out.println("\nCantidad total de pares: " + contenedor.obtenerTodosLosPares().size());
    }
}
