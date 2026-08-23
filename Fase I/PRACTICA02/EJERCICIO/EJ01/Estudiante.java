package EJ01;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Persona implements Reportable {

    // ===== Constante =====
    public static final int MAX_CREDITOS_SEMESTRE = 22;

    // ===== Variable de clase =====
    private static int totalEstudiantes = 0;

    // ===== Variables de instancia =====
    private String codigoEstudiante;
    private List<Curso> cursosInscritos;      // AGREGACIÓN: los cursos existen independientes del estudiante
    private HistorialAcademico historial;     // COMPOSICIÓN: nace y muere junto con el estudiante

    public Estudiante(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
        totalEstudiantes++;
        this.codigoEstudiante = "EST-" + String.format("%04d", totalEstudiantes);
        this.cursosInscritos = new ArrayList<>();
        this.historial = new HistorialAcademico(); // se crea aquí mismo: composición
    }

    public boolean inscribirCurso(Curso curso) {
        boolean seInscribio = curso.inscribirEstudiante(this);
        if (seInscribio && !cursosInscritos.contains(curso)) {
            cursosInscritos.add(curso);
        }
        return seInscribio;
    }

    public void registrarNota(String nombreCurso, double nota) {
        historial.agregarRegistro(nombreCurso, nota);
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    // ===== Método de clase =====
    public static int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    // ===== Polimorfismo: sobreescribe el método abstracto de Persona =====
    @Override
    public String mostrarInfo() {
        return "Estudiante [" + codigoEstudiante + "] " + getNombre() + " " + getApellido()
                + " - Cursos inscritos: " + cursosInscritos.size();
    }

    // ===== Implementación de la interfaz Reportable =====
    @Override
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Historial académico de ").append(getNombre()).append(" ").append(getApellido()).append(":\n");
        if (historial.getRegistros().isEmpty()) {
            sb.append("  (Sin notas registradas)\n");
        } else {
            for (String r : historial.getRegistros()) {
                sb.append("  - ").append(r).append("\n");
            }
        }
        return sb.toString();
    }
}