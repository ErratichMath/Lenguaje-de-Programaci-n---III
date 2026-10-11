package controlador;

import modelo.Empleado;
import modelo.EmpleadoModel;
import vista.EmpleadoView;

import java.io.IOException;

public class EmpleadoController {
    private EmpleadoModel modelo;
    private EmpleadoView vista;

    public EmpleadoController() {
        modelo = new EmpleadoModel();
        vista = new EmpleadoView();
    }

    public void iniciar() {
        cargarEmpleados();

        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();
            switch (opcion) {
                case 1:
                    listarEmpleados();
                    break;
                case 2:
                    agregarEmpleado();
                    break;
                case 3:
                    buscarEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    salir = true;
                    vista.mostrarMensaje("Programa finalizado");
                    break;
                default:
                    vista.mostrarMensaje("Opcion invalida, intenta de nuevo");
            }
        }
    }

    private void cargarEmpleados() {
        try {
            modelo.leerEmpleados();
            vista.mostrarMensaje("Empleados cargados desde el archivo:");
            vista.mostrarEmpleados(modelo.getEmpleados());
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo de empleados: " + e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            vista.mostrarMensaje("El archivo de empleados tiene un formato invalido");
        }
    }

    private void listarEmpleados() {
        vista.mostrarEmpleados(modelo.getEmpleados());
    }

    private void agregarEmpleado() {
        try {
            Empleado empleado = vista.solicitarDatosEmpleado();
            boolean agregado = modelo.agregarEmpleado(empleado);
            if (agregado) {
                vista.mostrarMensaje("Empleado agregado correctamente");
            } else {
                vista.mostrarMensaje("Ya existe un empleado con ese numero");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Dato invalido, no se pudo agregar el empleado");
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el empleado: " + e.getMessage());
        }
    }

    private void buscarEmpleado() {
        try {
            int numero = vista.solicitarNumero();
            Empleado empleado = modelo.buscarEmpleado(numero);
            if (empleado == null) {
                vista.mostrarMensaje("No se encontro un empleado con ese numero");
            } else {
                vista.mostrarMensaje(empleado.toString());
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Numero invalido");
        }
    }

    private void eliminarEmpleado() {
        try {
            int numero = vista.solicitarNumero();
            boolean eliminado = modelo.eliminarEmpleado(numero);
            if (eliminado) {
                vista.mostrarMensaje("Empleado eliminado correctamente");
            } else {
                vista.mostrarMensaje("No se encontro un empleado con ese numero");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Numero invalido");
        } catch (IOException e) {
            vista.mostrarMensaje("Error al actualizar el archivo: " + e.getMessage());
        }
    }
}
