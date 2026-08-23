package EJ01;

public class Main {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();

        // ---- Crear profesores ----
        Profesor profeMate = new Profesor("Carlos", "Vera", "cvera@ucsm.edu.pe", "Matemáticas");
        Profesor profeProg = new Profesor("Lucía", "Fernández", "lfernandez@ucsm.edu.pe", "Programación");
        sistema.registrarProfesor(profeMate);
        sistema.registrarProfesor(profeProg);

        // ---- Crear cursos (agregación: se les asigna un profesor ya existente) ----
        Curso calculo = new Curso("Cálculo I", Categoria.MATEMATICAS, profeMate);
        Curso java = new Curso("Programación en Java", Categoria.PROGRAMACION, profeProg);
        sistema.registrarCurso(calculo);
        sistema.registrarCurso(java);

        // ---- Crear estudiantes ----
        Estudiante e1 = new Estudiante("Ana", "García", "agarcia@ucsm.edu.pe");
        Estudiante e2 = new Estudiante("Luis", "Ramírez", "lramirez@ucsm.edu.pe");
        sistema.registrarEstudiante(e1);
        sistema.registrarEstudiante(e2);

        // ---- Inscribir estudiantes en cursos ----
        e1.inscribirCurso(calculo);
        e1.inscribirCurso(java);
        e2.inscribirCurso(java);

        // ---- Registrar notas (usa la composición Estudiante-HistorialAcademico) ----
        e1.registrarNota("Cálculo I", 16.5);
        e1.registrarNota("Programación en Java", 18.0);

        System.out.println("=== " + SistemaGestion.NOMBRE_INSTITUCION + " ===\n");

        System.out.println("--- Personas registradas (polimorfismo) ---");
        sistema.mostrarTodasLasPersonas();

        System.out.println("\n--- Cursos disponibles ---");
        for (Curso c : sistema.listarCursosDisponibles()) {
            System.out.println(c);
        }

        System.out.println("\n--- Reporte de estudiante (interfaz Reportable) ---");
        System.out.println(e1.generarReporte());

        System.out.println("--- Reporte de profesor (interfaz Reportable) ---");
        System.out.println(profeProg.generarReporte());

        System.out.println("--- Estadísticas de clase (variables static) ---");
        System.out.println("Total personas creadas: " + Persona.getTotalPersonas());
        System.out.println("Total estudiantes: " + Estudiante.getTotalEstudiantes());
        System.out.println("Total profesores: " + Profesor.getTotalProfesores());
        System.out.println("Total cursos: " + Curso.getTotalCursos());
    }
}