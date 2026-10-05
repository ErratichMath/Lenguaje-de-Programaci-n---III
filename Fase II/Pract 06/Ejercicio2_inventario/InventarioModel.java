import java.util.ArrayList;
import java.util.List;

/** MODELO: administra la lista de ítems. No imprime nada. */
public class InventarioModel {
    private final List<Item> items = new ArrayList<>();

    /** Si ya existe un ítem con ese nombre, suma la cantidad; si no, lo agrega. */
    public void agregarItem(Item item) {
        Item existente = buscarItem(item.getNombre());
        if (existente != null) existente.sumarCantidad(item.getCantidad());
        else items.add(item);
    }

    public boolean eliminarItem(Item item) { return items.remove(item); }

    public List<Item> obtenerItems() { return items; }

    /** Busca por nombre exacto (sin distinguir mayúsculas). Devuelve null si no existe. */
    public Item buscarItem(String nombre) {
        for (Item i : items) {
            if (i.getNombre().equalsIgnoreCase(nombre)) return i;
        }
        return null;
    }
}
