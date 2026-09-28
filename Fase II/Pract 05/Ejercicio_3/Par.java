/**
 * EJERCICIO 3 (clase de apoyo)
 * Clase genérica Par con el método esIgual (incluye lo del Ejercicio 1).
 */
public class Par<F, S> {
    private F primero;
    private S segundo;

    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public F getPrimero() { return primero; }
    public S getSegundo() { return segundo; }
    public void setPrimero(F primero) { this.primero = primero; }
    public void setSegundo(S segundo) { this.segundo = segundo; }

    @Override
    public String toString() {
        return String.format("(Primero: %s, Segundo: %s)", primero, segundo);
    }

    // NUEVO (Ejercicio 2): true si ambos pares tienen los mismos valores en el mismo orden
    public boolean esIgual(Par<F, S> otroPar) {
        boolean primerosIguales = (this.primero == null)
            ? otroPar.primero == null
            : this.primero.equals(otroPar.primero);

        boolean segundosIguales = (this.segundo == null)
            ? otroPar.segundo == null
            : this.segundo.equals(otroPar.segundo);

        return primerosIguales && segundosIguales;
    }
}
