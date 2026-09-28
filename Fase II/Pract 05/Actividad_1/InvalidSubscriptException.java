/**
 * Actividad 1 - Excepción personalizada para índices inválidos.
 */
public class InvalidSubscriptException extends RuntimeException {
    public InvalidSubscriptException(String mensaje) {
        super(mensaje);
    }
}
