package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoModel {
    private static final String ARCHIVO = "empleados.txt";
    private List<Empleado> empleados;

    public EmpleadoModel() {
        empleados = new ArrayList<>();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void leerEmpleados() throws IOException {
        empleados.clear();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.isBlank()) {
                    empleados.add(Empleado.desdeLinea(linea));
                }
            }
        }
    }

    public boolean agregarEmpleado(Empleado empleado) throws IOException {
        if (buscarEmpleado(empleado.getNumero()) != null) {
            return false;
        }
        empleados.add(empleado);
        guardar();
        return true;
    }

    public Empleado buscarEmpleado(int numero) {
        for (Empleado e : empleados) {
            if (e.getNumero() == numero) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarEmpleado(int numero) throws IOException {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado == null) {
            return false;
        }
        empleados.remove(empleado);
        guardar();
        return true;
    }

    private void guardar() throws IOException {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            for (Empleado e : empleados) {
                writer.write(e.toLinea() + "\n");
            }
        }
    }
}
