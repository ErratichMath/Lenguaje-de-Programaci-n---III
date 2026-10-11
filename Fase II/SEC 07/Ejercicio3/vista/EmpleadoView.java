package vista;

import modelo.Empleado;

import java.util.List;
import java.util.Scanner;

public class EmpleadoView {
    private Scanner scanner;

    public EmpleadoView() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== Gestion de empleados ===");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por su numero");
        System.out.println("4. Eliminar un empleado por su numero");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    public Empleado solicitarDatosEmpleado() {
        System.out.print("Numero: ");
        int numero = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Sueldo: ");
        double sueldo = Double.parseDouble(scanner.nextLine().trim());
        return new Empleado(numero, nombre, sueldo);
    }

    public int solicitarNumero() {
        System.out.print("Numero de empleado: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
