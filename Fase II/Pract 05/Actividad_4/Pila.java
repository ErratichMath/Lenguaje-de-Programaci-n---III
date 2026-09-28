/**
 * ACTIVIDAD 4 (incluye contains de la Actividad 2)
 * Pila genérica con contains(E elemento) y esIgual(Pila<E> otraPila).
 */
public class Pila<E> {
    private final int tamanio;   // capacidad de la pila
    private int superior;        // ubicación del elemento superior
    private E[] elementos;       // arreglo que almacena los elementos

    // constructor sin argumentos: tamaño predeterminado
    public Pila() {
        this(10);
    }

    // constructor con tamaño especificado
    @SuppressWarnings("unchecked")
    public Pila(int s) {
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }

    // mete un elemento; lanza ExcepcionPilaLlena si está llena
    public void push(E valorAMeter) {
        if (superior == tamanio - 1)
            throw new ExcepcionPilaLlena(String.format(
                "La Pila esta llena, no se puede meter %s", valorAMeter));
        elementos[++superior] = valorAMeter;
    }

    // saca y devuelve el último elemento; lanza ExcepcionPilaVacia si está vacía
    public E pop() {
        if (superior == -1)
            throw new ExcepcionPilaVacia("Pila vacia, no se puede sacar");
        return elementos[superior--];
    }

    // NUEVO: busca el elemento desde el tope hacia el fondo sin modificar la pila
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i] == null) {
                if (elemento == null) return true;
            } else if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    } // fin del método contains

    // NUEVO (Actividad 4): compara dos pilas sin modificarlas
    public boolean esIgual(Pila<E> otraPila) {
        // si el número de elementos es distinto, no pueden ser iguales
        if (this.superior != otraPila.superior) {
            return false;
        }

        // compara elemento por elemento, en el mismo orden
        for (int i = 0; i <= this.superior; i++) {
            E elementoPropio = this.elementos[i];
            E elementoOtro = otraPila.elementos[i];

            if (elementoPropio == null) {
                if (elementoOtro != null) return false;
            } else if (!elementoPropio.equals(elementoOtro)) {
                return false;
            }
        }
        return true; // mismo tamaño y mismos elementos en el mismo orden
    } // fin del método esIgual
}
