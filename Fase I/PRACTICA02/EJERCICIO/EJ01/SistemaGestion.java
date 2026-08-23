package EJ01;

import java.util.ArrayList;
import java.util.List;

public class SistemaGestion {

    // ===== Constante =====
    public static final String NOMBRE_INSTITUCION = "Universidad Católica de Santa María";

    // ===== Variables de instancia =====
    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private List<Curso> cursos;

    public SistemaGestion() {
        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public void registrarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }

    public void registrarProfesor(Profesor p) {
        profesores.add(p);
    }

    public void registrarCurso(Curso c) {
        cursos.add(c);
    }

    public List<Curso> listarCursosDisponibles() {
        List<Curso> disponibles = new ArrayList<>();
        for (Curso c : cursos) {
            if (c.estaDisponible()) {
                disponibles.add(c);
            }
        }
        return disponibles;
    }

    /**
     * Aquí se evidencia el polimorfismo: se arma una lista de Persona
     * (mezclando Estudiante y Profesor) y al llamar mostrarInfo() sobre
     * cada uno, Java ejecuta la versión correcta según el tipo real del objeto.
     */
    public void mostrarTodasLasPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.addAll(estudiantes);
        personas.addAll(profesores);
        for (Persona p : personas) {
            System.out.println(p.mostrarInfo());
        }
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Curso> getCursos() {
        return cursos;
    }
}