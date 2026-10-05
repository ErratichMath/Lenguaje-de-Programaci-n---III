/** MODELO: jugador con inventario. Su ataque depende del arma equipada. */
public class Jugador extends Personaje {
    private final InventarioModel inventario = new InventarioModel();
    private Item armaEquipada; // null = pelea con los puños

    public Jugador(String nombre, int salud, int nivel) {
        super(nombre, salud, nivel);
    }

    /** Ataca a un enemigo. Daño = poder del arma equipada (1 si no tiene) + nivel. Devuelve el daño causado. */
    public int atacar(Enemigo enemigo) {
        // Si el arma fue eliminada del inventario, deja de estar equipada
        if (armaEquipada != null && !inventario.obtenerItems().contains(armaEquipada)) {
            armaEquipada = null;
        }
        int base = (armaEquipada != null) ? armaEquipada.getPoder() : 1;
        return enemigo.recibirDano(base + nivel);
    }

    /** Usa un ítem: un arma se equipa; una poción cura. Devuelve el mensaje del resultado. */
    public String usarObjeto(Item item) {
        if (item.getTipo() == TipoItem.ARMA) {
            armaEquipada = item;
            return nombre + " equipa " + item.getNombre() + ".";
        }
        if (!item.usarItem()) return "No quedan unidades de " + item.getNombre() + ".";

        int antes = salud;
        salud = Math.min(saludMaxima, salud + item.getPoder());
        String mensaje = nombre + " usa " + item.getNombre() + " y recupera " + (salud - antes) + " de salud.";
        if (item.getCantidad() == 0) inventario.eliminarItem(item); // poción agotada
        return mensaje;
    }

    public InventarioModel getInventario() { return inventario; }
    public Item getArmaEquipada() { return armaEquipada; }
}
