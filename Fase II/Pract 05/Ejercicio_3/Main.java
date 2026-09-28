/**
 * EJERCICIO 3
 * Método genérico estático imprimirPar, probado con distintos tipos de pares.
 */
public class Main {

    // método genérico estático imprimirPar
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println(par);
    } // fin del método imprimirPar

    public static void main(String[] args) {
        Par<String, Integer> parNombreEdad = new Par<>("Carlos", 28);
        Par<Double, Boolean> parPrecioDisponible = new Par<>(19.99, true);
        Par<Persona, Integer> parPersonaCodigo = new Par<>(new Persona("Marta", 22), 101);

        imprimirPar(parNombreEdad);        // String, Integer
        imprimirPar(parPrecioDisponible);  // Double, Boolean
        imprimirPar(parPersonaCodigo);     // Persona, Integer
    }
}
