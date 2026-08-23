package ACT04;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta; // relación de composición: la Cuenta no existe sin la Persona

    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        // El número de cuenta se determina a partir del id de la persona
        int numeroCuenta = 1000 + id;
        this.cuenta = new Cuenta(numeroCuenta);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    @Override
    public String toString() {
        return "Persona [ID: " + id
                + ", Nombre: " + nombre + " " + apellido
                + ", " + cuenta.toString()
                + "]";
    }
}