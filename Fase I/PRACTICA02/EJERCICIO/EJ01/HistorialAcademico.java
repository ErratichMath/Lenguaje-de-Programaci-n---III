package EJ01;

import java.util.ArrayList;
import java.util.List;

/**
 * Este objeto solo tiene sentido "dentro" de un Estudiante.
 * Se crea junto con el Estudiante y no se comparte ni se accede
 * desde fuera de forma independiente -> relación de COMPOSICIÓN.
 */
public class HistorialAcademico {
    private List<String> registros;

    public HistorialAcademico() {
        this.registros = new ArrayList<>();
    }

    public void agregarRegistro(String nombreCurso, double nota) {
        registros.add(nombreCurso + ": " + nota);
    }

    public List<String> getRegistros() {
        return registros;
    }
}