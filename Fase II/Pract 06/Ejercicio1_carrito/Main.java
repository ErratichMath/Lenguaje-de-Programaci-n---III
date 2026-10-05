public class Main {
    public static void main(String[] args) {
        CarritoModelo modelo = new CarritoModelo();
        // Productos de ejemplo
        modelo.agregarProducto("Laptop", 2500.00, 5);
        modelo.agregarProducto("Mouse", 45.50, 20);
        modelo.agregarProducto("Teclado", 80.00, 10);

        CarritoVista vista = new CarritoVista();
        new CarritoControlador(modelo, vista).iniciar();
    }
}
