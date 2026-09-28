/**
 * Excepción lanzada al intentar meter un elemento en una Pila llena.
 */
public class ExcepcionPilaLlena extends RuntimeException {
    public ExcepcionPilaLlena(String mensaje) {
        super(mensaje);
    }
}
