/**
 * ACTIVIDAD 1
 * Método genérico imprimirArreglo y su versión sobrecargada con
 * subíndices inferior y superior.
 */
public class PruebaMetodoGenerico {

    // método genérico imprimirArreglo (versión original)
    public static <E> void imprimirArreglo(E[] arregloEntrada) {
        // muestra los elementos del arreglo
        for (E elemento : arregloEntrada)
            System.out.printf("%s ", elemento);
        System.out.println();
    } // fin del método imprimirArreglo

    // método sobrecargado: imprime solo el rango [inferior, superior]
    // devuelve la cantidad de elementos impresos
    public static <E> int imprimirArreglo(E[] arregloEntrada, int subindiceInferior, int subindiceSuperior) {
        // validaciones: fuera de rango o superior <= inferior
        if (subindiceInferior < 0 || subindiceSuperior >= arregloEntrada.length
                || subindiceSuperior <= subindiceInferior) {
            throw new InvalidSubscriptException(
                "Indices invalidos: inferior=" + subindiceInferior + ", superior=" + subindiceSuperior);
        }

        int contador = 0;
        for (int i = subindiceInferior; i <= subindiceSuperior; i++) {
            System.out.printf("%s ", arregloEntrada[i]);
            contador++;
        }
        System.out.println();
        return contador;
    } // fin del método imprimirArreglo sobrecargado

    public static void main(String[] args) {
        // crea arreglos de objetos Integer, Double y Character
        Integer[] arregloInteger = { 1, 2, 3, 4, 5, 6 };
        Double[] arregloDouble = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7 };
        Character[] arregloCharacter = { 'H', 'O', 'L', 'A' };

        System.out.println("Arreglo completo de enteros:");
        imprimirArreglo(arregloInteger);

        System.out.println("\nSolo una parte del arreglo de enteros (indices 1 a 4):");
        int cantidad = imprimirArreglo(arregloInteger, 1, 4);
        System.out.println("Elementos impresos: " + cantidad);

        System.out.println("\nProbando con indices invalidos:");
        try {
            imprimirArreglo(arregloDouble, 3, 2); // superior <= inferior
        } catch (InvalidSubscriptException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            imprimirArreglo(arregloCharacter, 0, 10); // fuera de rango
        } catch (InvalidSubscriptException e) {
            System.out.println("Error: " + e.getMessage());
        }
    } // fin de main
} // fin de la clase PruebaMetodoGenerico
