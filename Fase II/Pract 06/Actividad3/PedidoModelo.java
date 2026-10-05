import java.util.*;

/**
 * MODELO: contiene la lista de pedidos y toda la lógica de negocio.
 * No imprime nada ni conoce la vista.
 */
public class PedidoModelo {
    private final List<Pedido> pedidos = new ArrayList<>();   // pedidos activos
    private final List<Pedido> historial = new ArrayList<>(); // completados o eliminados

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /** Busca un pedido activo por id; devuelve null si no existe. */
    public Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    /** Elimina un pedido: sale de la lista activa y pasa al historial. */
    public boolean eliminarPedido(int id) {
        Pedido p = buscarPorId(id);
        if (p == null) return false;
        p.setEstado(Estado.ELIMINADO);
        pedidos.remove(p);
        historial.add(p);
        return true;
    }

    public boolean actualizarPedido(int id, String nuevoNombre) {
        Pedido p = buscarPorId(id);
        if (p == null) return false;
        p.setNombrePlato(nuevoNombre);
        return true;
    }

    /** Marca un pedido pendiente como completado y lo registra en el historial. */
    public boolean completarPedido(int id) {
        Pedido p = buscarPorId(id);
        if (p == null || p.getEstado() != Estado.PENDIENTE) return false;
        p.setEstado(Estado.COMPLETADO);
        historial.add(p);
        return true;
    }

    /** Busca por coincidencia parcial en nombre o tipo (sin distinguir mayúsculas). */
    public List<Pedido> buscar(String texto) {
        String t = texto.toLowerCase();
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getNombrePlato().toLowerCase().contains(t)
                    || p.getTipo().toLowerCase().contains(t)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Pedido> getPorEstado(Estado estado) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado() == estado) resultado.add(p);
        }
        return resultado;
    }

    public int contarTotal() { return pedidos.size(); }

    public int contarPendientes() { return getPorEstado(Estado.PENDIENTE).size(); }

    /** Cantidad de pedidos activos agrupados por tipo. */
    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> conteo = new TreeMap<>();
        for (Pedido p : pedidos) {
            conteo.merge(p.getTipo(), 1, Integer::sum);
        }
        return conteo;
    }

    public List<Pedido> getPedidos() { return pedidos; }
    public List<Pedido> getHistorial() { return historial; }
}
