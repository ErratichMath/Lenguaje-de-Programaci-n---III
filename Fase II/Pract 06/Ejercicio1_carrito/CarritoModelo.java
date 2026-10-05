import java.util.ArrayList;
import java.util.List;

/**
 * MODELO: catálogo, carrito, descuentos, envío e historial.
 * Contiene toda la lógica de negocio; no imprime nada.
 */
public class CarritoModelo {
    private static final double ENVIO_GRATIS_DESDE = 100.0; // monto (tras descuento) para envío gratis
    private static final double COSTO_ENVIO = 10.0;

    private final List<Producto> catalogo = new ArrayList<>();
    private final List<LineaCarrito> carrito = new ArrayList<>();
    private final List<Compra> historial = new ArrayList<>();
    private int porcentajeDescuento = 0;

    // ---------- Catálogo ----------
    public void agregarProducto(String nombre, double precio, int stock) {
        catalogo.add(new Producto(nombre, precio, stock));
    }

    public Producto buscarProducto(int id) {
        for (Producto p : catalogo) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // ---------- Carrito ----------
    private LineaCarrito buscarLinea(int idProducto) {
        for (LineaCarrito l : carrito) {
            if (l.getProducto().getId() == idProducto) return l;
        }
        return null;
    }

    /** Agrega un producto al carrito. Devuelve null si todo salió bien, o el mensaje de error. */
    public String agregarAlCarrito(int idProducto, int cantidad) {
        Producto p = buscarProducto(idProducto);
        if (p == null) return "No existe un producto con ese id.";
        if (cantidad <= 0) return "La cantidad debe ser mayor a 0.";

        LineaCarrito existente = buscarLinea(idProducto);
        int yaEnCarrito = (existente == null) ? 0 : existente.getCantidad();
        if (yaEnCarrito + cantidad > p.getStock()) {
            return "Stock insuficiente. Disponible: " + p.getStock();
        }
        if (existente == null) carrito.add(new LineaCarrito(p, cantidad));
        else existente.agregarCantidad(cantidad);
        return null;
    }

    public boolean eliminarDelCarrito(int idProducto) {
        return carrito.removeIf(l -> l.getProducto().getId() == idProducto);
    }

    // ---------- Cálculos ----------
    public double getSubtotal() {
        double suma = 0;
        for (LineaCarrito l : carrito) suma += l.getSubtotal();
        return suma;
    }

    /** Códigos válidos: DESC10 (10%) y DESC20 (20%). */
    public boolean aplicarDescuento(String codigo) {
        switch (codigo.toUpperCase()) {
            case "DESC10": porcentajeDescuento = 10; return true;
            case "DESC20": porcentajeDescuento = 20; return true;
            default: return false;
        }
    }

    public double getMontoDescuento() { return getSubtotal() * porcentajeDescuento / 100.0; }

    /** Envío gratis si el monto tras descuento llega al mínimo; 0 si el carrito está vacío. */
    public double calcularEnvio() {
        if (carrito.isEmpty()) return 0;
        return (getSubtotal() - getMontoDescuento() >= ENVIO_GRATIS_DESDE) ? 0 : COSTO_ENVIO;
    }

    public double getTotal() { return getSubtotal() - getMontoDescuento() + calcularEnvio(); }

    // ---------- Compra ----------
    /** Cierra la compra: descuenta stock, guarda en historial y vacía el carrito. Null si estaba vacío. */
    public Compra realizarCompra() {
        if (carrito.isEmpty()) return null;
        List<String> detalle = new ArrayList<>();
        for (LineaCarrito l : carrito) {
            l.getProducto().reducirStock(l.getCantidad());
            detalle.add(l.toString());
        }
        Compra compra = new Compra(detalle, getSubtotal(), getMontoDescuento(), calcularEnvio(), getTotal());
        historial.add(compra);
        carrito.clear();
        porcentajeDescuento = 0;
        return compra;
    }

    public List<Producto> getCatalogo() { return catalogo; }
    public List<LineaCarrito> getCarrito() { return carrito; }
    public List<Compra> getHistorial() { return historial; }
}
