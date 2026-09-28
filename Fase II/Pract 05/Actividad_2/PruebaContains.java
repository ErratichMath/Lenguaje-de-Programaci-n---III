/**
 * ACTIVIDAD 2 - Prueba del método contains de la Pila.
 */
public class PruebaContains {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>(5);
        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("¿Contiene 20? " + pila.contains(20)); // true
        System.out.println("¿Contiene 99? " + pila.contains(99)); // false

        // verificamos que el estado no cambió
        System.out.println("Elemento en el tope: " + pila.pop()); // sigue siendo 30
    }
}
