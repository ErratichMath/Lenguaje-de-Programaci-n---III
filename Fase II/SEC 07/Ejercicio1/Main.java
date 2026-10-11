public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        gestor.agregarPersonaje(new Personaje("Aldric", 100, 20, 10, 5));
        gestor.agregarPersonaje(new Personaje("Mira", 80, 25, 8, 7));

        System.out.println("=== Personajes registrados ===");
        gestor.mostrarPersonajes();

        gestor.modificarPersonaje("Mira", 90, 28, 8, 7);
        System.out.println("\n=== Despues de modificar a Mira ===");
        gestor.mostrarPersonajes();

        gestor.eliminarPersonaje("Aldric");
        System.out.println("\n=== Despues de eliminar a Aldric ===");
        gestor.mostrarPersonajes();
    }
}
