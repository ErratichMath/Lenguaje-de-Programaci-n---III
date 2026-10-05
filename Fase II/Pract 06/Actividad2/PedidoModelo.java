import java.util.*;

/** MODELO: lista de pedidos y lógica de negocio. No imprime nada ni conoce la vista. */
public class PedidoModelo {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /** Busca un pedido por id; devuelve null si no existe. */
    public Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean eliminarPedido(int id) {
        Pedido p = buscarPorId(id);
        return p != null && pedidos.remove(p);
    }

    public boolean actualizarPedido(int id, String nuevoNombre) {
        Pedido p = buscarPorId(id);
        if (p == null) return false;
        p.setNombrePlato(nuevoNombre);
        return true;
    }

    /** Busca por coincidencia parcial en nombre o tipo (sin distinguir mayúsculas). */
    public List<Pedido> buscar(String texto) {
        String t = texto.toLowerCase();
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getNombrePlato().toLowerCase().contains(t) || p.getTipo().toLowerCase().contains(t)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public int contarTotal() { return pedidos.size(); }

    /** Cantidad de pedidos agrupados por tipo. */
    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> conteo = new TreeMap<>();
        for (Pedido p : pedidos) {
            conteo.merge(p.getTipo(), 1, Integer::sum);
        }
        return conteo;
    }

    public List<Pedido> getPedidos() { return pedidos; }
}
