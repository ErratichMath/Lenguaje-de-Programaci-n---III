package EJ01;

public abstract class Persona {

    // ===== Variable de clase =====
    // Cuenta cuántas personas (estudiantes + profesores) se han creado en el sistema
    private static int totalPersonas = 0;

    // ===== Variables de instancia =====
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    public Persona(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        totalPersonas++;
        this.id = totalPersonas; // id autogenerado a partir del contador de clase
    }

    // ===== Getters (instancia) =====
    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ===== Método de clase =====
    public static int getTotalPersonas() {
        return totalPersonas;
    }

    // Método abstracto: cada subclase (Estudiante, Profesor) lo implementa
    // de forma distinta -> este es el método polimórfico de la jerarquía.
    public abstract String mostrarInfo();
}