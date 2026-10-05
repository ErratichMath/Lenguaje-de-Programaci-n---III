/** MODELO: producto del catálogo de la tienda. */
public class Producto {
    private static int contador = 1; // genera ids únicos

    private final int id;
    private final String nombre;
    private final double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void reducirStock(int cantidad) { stock -= cantidad; }

    @Override
    public String toString() {
        return String.format("#%d %s - S/ %.2f (stock: %d)", id, nombre, precio, stock);
    }
}
