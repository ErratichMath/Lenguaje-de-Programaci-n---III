/**
 * Excepción lanzada al intentar sacar un elemento de una Pila vacía.
 */
public class ExcepcionPilaVacia extends RuntimeException {
    public ExcepcionPilaVacia(String mensaje) {
        super(mensaje);
    }
}
