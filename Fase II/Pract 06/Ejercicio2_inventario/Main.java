public class Main {
    public static void main(String[] args) {
        InventarioModel modelo = new InventarioModel();
        // Ítems de ejemplo
        modelo.agregarItem(new Item("Espada", 1, TipoItem.ARMA, "Espada de hierro", 8));
        modelo.agregarItem(new Item("Pocion de vida", 3, TipoItem.POCION, "Restaura 20 de salud", 20));

        InventarioView vista = new InventarioView();
        new InventarioController(modelo, vista).iniciar();
    }
}
