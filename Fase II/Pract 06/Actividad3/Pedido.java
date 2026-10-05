/**
 * MODELO: representa un pedido del restaurante.
 * Actividad 2: agrega 'tipo'. Actividad 3: agrega 'estado'.
 */
public class Pedido {
    private static int contadorId = 1; // genera ids únicos

    private final int id;
    private String nombrePlato;
    private final String tipo;   // Ej: Entrada, Fondo, Postre, Bebida
    private Estado estado;

    public Pedido(String nombrePlato, String tipo) {
        this.id = contadorId++;
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
        this.estado = Estado.PENDIENTE; // todo pedido nuevo inicia pendiente
    }

    public int getId() { return id; }
    public String getNombrePlato() { return nombrePlato; }
    public void setNombrePlato(String nombrePlato) { this.nombrePlato = nombrePlato; }
    public String getTipo() { return tipo; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "#" + id + " " + nombrePlato + " (" + tipo + ") - " + estado;
    }
}
