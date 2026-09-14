import java.util.NoSuchElementException;

/*
 * EJERCICIO 4: Registro de Estudiantes
 * ---------------------------------------
 * agregarEstudiante() lanza IllegalArgumentException si el nombre
 * es nulo o vacio.
 * buscarEstudiante() lanza NoSuchElementException si el estudiante
 * no esta en el arreglo.
 */

class RegistroEstudiantes {
    private String[] estudiantes;
    private int cantidad;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        cantidad = 0;
    }

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del estudiante no puede ser nulo ni vacio");
        }
        if (cantidad == estudiantes.length) {
            throw new IllegalStateException("El registro esta lleno");
        }
        estudiantes[cantidad] = nombre;
        cantidad++;
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                return estudiantes[i];
            }
        }
        throw new NoSuchElementException(
                "No se encontro al estudiante: " + nombre);
    }
}

public class Ejercicio4 {

    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes(5);

        String[] nombresAAgregar = {"Ana", "Luis", "", null, "Maria"};

        for (String nombre : nombresAAgregar) {
            try {
                registro.agregarEstudiante(nombre);
                System.out.println("Agregado: " + nombre);
            } catch (IllegalArgumentException e) {
                System.out.println("Error al agregar: " + e.getMessage());
            }
        }

        String[] busquedas = {"Ana", "Pedro", "maria"};

        for (String nombre : busquedas) {
            try {
                String encontrado = registro.buscarEstudiante(nombre);
                System.out.println("Encontrado: " + encontrado);
            } catch (NoSuchElementException e) {
                System.out.println("Error al buscar: " + e.getMessage());
            }
        }
    }
}
