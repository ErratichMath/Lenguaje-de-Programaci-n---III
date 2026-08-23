package EJ01;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Persona implements Reportable {

    // ===== Variable de clase =====
    private static int totalProfesores = 0;

    // ===== Variables de instancia =====
    private String codigoProfesor;
    private String especialidad;
    private List<Curso> cursosAsignados; // AGREGACIÓN: los cursos existen independientes del profesor

    public Profesor(String nombre, String apellido, String email, String especialidad) {
        super(nombre, apellido, email);
        totalProfesores++;
        this.codigoProfesor = "PROF-" + String.format("%04d", totalProfesores);
        this.especialidad = especialidad;
        this.cursosAsignados = new ArrayList<>();
    }

    public void asignarCurso(Curso curso) {
        if (!cursosAsignados.contains(curso)) {
            cursosAsignados.add(curso);
        }
    }

    public String getCodigoProfesor() {
        return codigoProfesor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }

    // ===== Método de clase =====
    public static int getTotalProfesores() {
        return totalProfesores;
    }

    // ===== Polimorfismo: sobreescribe el método abstracto de Persona =====
    @Override
    public String mostrarInfo() {
        return "Profesor [" + codigoProfesor + "] " + getNombre() + " " + getApellido()
                + " - Especialidad: " + especialidad
                + " - Cursos a cargo: " + cursosAsignados.size();
    }

    // ===== Implementación de la interfaz Reportable =====
    @Override
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cursos a cargo de ").append(getNombre()).append(" ").append(getApellido()).append(":\n");
        if (cursosAsignados.isEmpty()) {
            sb.append("  (Sin cursos asignados)\n");
        } else {
            for (Curso c : cursosAsignados) {
                sb.append("  - ").append(c.getNombre()).append("\n");
            }
        }
        return sb.toString();
    }
}