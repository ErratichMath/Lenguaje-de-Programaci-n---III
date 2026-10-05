/**
 * MODELO: ítem del inventario (Nombre, Cantidad, Tipo, Descripción, usarItem()).
 * 'poder' es el daño del arma o la curación de la poción (se usa en el ejercicio 3).
 */
public class Item {
    private final String nombre;
    private int cantidad;
    private final TipoItem tipo;
    private final String descripcion;
    private final int poder;

    public Item(String nombre, int cantidad, TipoItem tipo, String descripcion, int poder) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.poder = poder;
    }

    /** Usa el ítem: las pociones se consumen (-1 unidad), las armas no. Devuelve false si no hay unidades. */
    public boolean usarItem() {
        if (cantidad <= 0) return false;
        if (tipo == TipoItem.POCION) cantidad--;
        return true;
    }

    public void sumarCantidad(int extra) { cantidad += extra; }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public TipoItem getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public int getPoder() { return poder; }

    @Override
    public String toString() {
        return nombre + " x" + cantidad + " [" + tipo.getEtiqueta() + "]";
    }
}
