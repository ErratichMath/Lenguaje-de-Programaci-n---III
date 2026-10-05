/** MODELO: una línea del carrito (producto + cantidad). */
public class LineaCarrito {
    private final Producto producto;
    private int cantidad;

    public LineaCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public void agregarCantidad(int extra) { cantidad += extra; }

    public double getSubtotal() { return producto.getPrecio() * cantidad; }

    @Override
    public String toString() {
        return String.format("%s x%d = S/ %.2f", producto.getNombre(), cantidad, getSubtotal());
    }
}
