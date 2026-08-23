package ACT01;

public class EjemploCoche {
    public static void main(String[] args) {

        // Crear objetos coche usando el constructor de 4 parámetros
        Coche cocheDeportivo = new Coche("Ferrari", "F8 Tributo", 2021, 280000.0);
        Coche cocheTodoTerreno = new Coche("Toyota", "Land Cruiser", 2008, 45000.0);

        cocheDeportivo.color = "Rojo";
        cocheTodoTerreno.color = "Gris";

        System.out.println("=== Datos iniciales ===");
        mostrarDatos(cocheDeportivo);
        mostrarDatos(cocheTodoTerreno);

        // Encender los coches
        System.out.println("\n=== Encendiendo ===");
        cocheDeportivo.encender();
        cocheTodoTerreno.encender();

        // Acelerar y frenar los coches
        System.out.println("\n=== Acelerando y frenando ===");
        cocheDeportivo.acelerar();
        cocheDeportivo.frenar();
        cocheTodoTerreno.acelerar();
        cocheTodoTerreno.frenar();

        // Apagar los coches
        System.out.println("\n=== Apagando ===");
        cocheDeportivo.apagar();
        cocheTodoTerreno.apagar();

        // Probar aplicarDescuento()
        System.out.println("\n=== Aplicando descuento (10%) ===");
        boolean descuentoDeportivo = cocheDeportivo.aplicarDescuento(10);
        boolean descuentoTodoTerreno = cocheTodoTerreno.aplicarDescuento(10);

        System.out.println(cocheDeportivo.getModelo() + " (" + cocheDeportivo.getAnoFabricacion()
                + ") -> ¿Se aplicó descuento? " + descuentoDeportivo
                + " | Precio final: " + cocheDeportivo.getPrecio());

        System.out.println(cocheTodoTerreno.getModelo() + " (" + cocheTodoTerreno.getAnoFabricacion()
                + ") -> ¿Se aplicó descuento? " + descuentoTodoTerreno
                + " | Precio final: " + cocheTodoTerreno.getPrecio());

        // Probar el constructor por defecto
        System.out.println("\n=== Coche creado con constructor por defecto ===");
        Coche cocheVacio = new Coche();
        mostrarDatos(cocheVacio);
    }

    private static void mostrarDatos(Coche c) {
        System.out.println("Marca: " + c.getMarca()
                + " | Modelo: " + c.getModelo()
                + " | Año: " + c.getAnoFabricacion()
                + " | Precio: " + c.getPrecio()
                + " | Color: " + c.color);
    }
}