/** MODELO: pedido del restaurante. Actividad 2: ahora tiene nombre y tipo. */
public class Pedido {
    private static int contadorId = 1; // genera ids únicos

    private final int id;
    private String nombrePlato;
    private final String tipo; // Ej: Entrada, Fondo, Postre, Bebida

    public Pedido(String nombrePlato, String tipo) {
        this.id = contadorId++;
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getNombrePlato() { return nombrePlato; }
    public void setNombrePlato(String nombrePlato) { this.nombrePlato = nombrePlato; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return "#" + id + " " + nombrePlato + " (" + tipo + ")";
    }
}
