public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        gestor.cargarPersonajesAleatorios(3);
        gestor.agregarPersonaje(new Personaje("Aldric", 100, 20, 10, 5));

        System.out.println("=== Personajes registrados ===");
        gestor.mostrarPersonajes();

        System.out.println();
        gestor.filtrarPorAtributo("ataque");

        gestor.actualizarAtributo("Aldric", "defensa", 15);
        gestor.subirNivelPersonaje("Aldric");

        System.out.println("\n=== Despues de actualizar y subir de nivel a Aldric ===");
        System.out.println(gestor.buscarPersonaje("Aldric"));

        System.out.println("\n=== Estadisticas ===");
        gestor.mostrarEstadisticas();
    }
}
