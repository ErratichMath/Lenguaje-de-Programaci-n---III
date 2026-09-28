/**
 * ACTIVIDAD 4 - Prueba del método esIgual de la Pila.
 */
public class PruebaEsIgual {
    public static void main(String[] args) {
        Pila<Integer> pila1 = new Pila<>(5);
        pila1.push(10); pila1.push(20); pila1.push(30);

        Pila<Integer> pila2 = new Pila<>(5);
        pila2.push(10); pila2.push(20); pila2.push(30);

        Pila<Integer> pila3 = new Pila<>(5);
        pila3.push(10); pila3.push(99); pila3.push(30);

        Pila<Integer> pila4 = new Pila<>(5);
        pila4.push(10); pila4.push(20);

        System.out.println("pila1 == pila2: " + pila1.esIgual(pila2)); // true
        System.out.println("pila1 == pila3: " + pila1.esIgual(pila3)); // false
        System.out.println("pila1 == pila4: " + pila1.esIgual(pila4)); // false

        // verificamos que ninguna pila cambió de estado
        System.out.println("Tope de pila1 tras comparar: " + pila1.pop()); // 30
    }
}
