package modelo;

public class Empleado {
    private int numero;
    private String nombre;
    private double sueldo;

    public Empleado(int numero, String nombre, double sueldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String toLinea() {
        return numero + "," + nombre + "," + sueldo;
    }

    public static Empleado desdeLinea(String linea) {
        String[] partes = linea.split(",");
        return new Empleado(Integer.parseInt(partes[0]), partes[1], Double.parseDouble(partes[2]));
    }

    @Override
    public String toString() {
        return "Numero: " + numero + " - Nombre: " + nombre + " - Sueldo: S/" + sueldo;
    }
}
