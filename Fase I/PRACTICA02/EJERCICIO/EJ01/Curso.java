package EJ01;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    // ===== Constante =====
    public static final int CAPACIDAD_MAXIMA = 30;

    // ===== Variable de clase =====
    private static int totalCursos = 0;

    // ===== Variables de instancia =====
    private String codigoCurso;
    private String nombre;
    private Categoria categoria;
    private Profesor profesor;                    // AGREGACIÓN: el profesor existe independiente del curso
    private List<Estudiante> estudiantesInscritos; // AGREGACIÓN: los estudiantes existen independientes del curso

    public Curso(String nombre, Categoria categoria, Profesor profesor) {
        totalCursos++;
        this.codigoCurso = "CUR-" + String.format("%04d", totalCursos);
        this.nombre = nombre;
        this.categoria = categoria;
        this.profesor = profesor;
        this.estudiantesInscritos = new ArrayList<>();

        if (profesor != null) {
            profesor.asignarCurso(this);
        }
    }

    public boolean inscribirEstudiante(Estudiante estudiante) {
        if (estudiantesInscritos.size() >= CAPACIDAD_MAXIMA) {
            return false; // curso lleno
        }
        if (!estudiantesInscritos.contains(estudiante)) {
            estudiantesInscritos.add(estudiante);
        }
        return true;
    }

    public boolean estaDisponible() {
        return estudiantesInscritos.size() < CAPACIDAD_MAXIMA;
    }

    public int getCantidadMatriculados() {
        return estudiantesInscritos.size();
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public List<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    // ===== Método de clase =====
    public static int getTotalCursos() {
        return totalCursos;
    }

    @Override
    public String toString() {
        return "[" + codigoCurso + "] " + nombre + " (" + categoria + ") - Profesor: "
                + (profesor != null ? profesor.getNombre() : "Sin asignar")
                + " - Matriculados: " + estudiantesInscritos.size() + "/" + CAPACIDAD_MAXIMA;
    }
}