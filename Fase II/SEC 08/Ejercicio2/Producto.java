/** Registro de la tabla producto (4 campos). */
public class Producto {
    private final int id;
    private final String nombre;
    private final String categoria;
    private final double precio;

    public Producto(int id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }

    /** Devuelve el valor de un campo a partir de su nombre. */
    public Object getCampo(String campo) {
        switch (campo.trim().toLowerCase()) {
            case "id":        return id;
            case "nombre":    return nombre;
            case "categoria": return categoria;
            case "precio":    return precio;
            default: throw new IllegalArgumentException("Campo inexistente: " + campo);
        }
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + categoria + " | " + precio;
    }
}
