import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 2: clase gestora.
 * Guarda los registros de la tabla en una lista de objetos y permite hacer
 * "consultas tipo SQL" resueltas con lógica Java (sin usar SELECT con WHERE/ORDER/LIMIT).
 */
public class GestorProductos {

    public static final List<String> CAMPOS = List.of("id", "nombre", "categoria", "precio");

    private final List<Producto> registros = new ArrayList<>();

    /** Carga todos los registros de la tabla en memoria. */
    public void cargar(Connection con) throws SQLException {
        registros.clear();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, nombre, categoria, precio FROM producto")) {
            while (rs.next()) {
                registros.add(new Producto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("categoria"), rs.getDouble("precio")));
            }
        }
    }

    /**
     * Consulta sobre el arreglo de objetos.
     *
     * @param campos      a) campos a mostrar (vacío o null = todos)
     * @param campoCond   b) campo que debe cumplir una condición (vacío o null = sin condición)
     * @param operador    b) =, !=, >, <, >=, <= (o LIKE para texto: "contiene")
     * @param valorCond   b) valor contra el que se compara
     * @param campoOrden  c) campo por el cual ordenar (vacío o null = sin orden)
     * @param ascendente  c) true = ascendente, false = descendente
     * @param limite      d) máximo de registros a mostrar (<= 0 = sin límite)
     * @return lista de filas; cada fila es campo -> valor (solo los campos pedidos)
     */
    public List<Map<String, Object>> consultar(List<String> campos,
                                               String campoCond, String operador, String valorCond,
                                               String campoOrden, boolean ascendente,
                                               int limite) {
        // validar campos pedidos
        List<String> proyeccion = (campos == null || campos.isEmpty()) ? CAMPOS : campos;
        for (String c : proyeccion) {
            validarCampo(c);
        }

        // b) condición (WHERE)
        List<Producto> resultado = registros.stream()
                .filter(p -> campoCond == null || campoCond.isBlank()
                        || cumple(p, campoCond, operador, valorCond))
                .collect(Collectors.toList());

        // c) orden (ORDER BY)
        if (campoOrden != null && !campoOrden.isBlank()) {
            validarCampo(campoOrden);
            Comparator<Producto> cmp = (a, b) -> comparar(a.getCampo(campoOrden), b.getCampo(campoOrden));
            if (!ascendente) {
                cmp = cmp.reversed();
            }
            resultado.sort(cmp);
        }

        // d) límite (LIMIT)
        if (limite > 0 && resultado.size() > limite) {
            resultado = resultado.subList(0, limite);
        }

        // a) proyección (SELECT campos)
        List<Map<String, Object>> filas = new ArrayList<>();
        for (Producto p : resultado) {
            Map<String, Object> fila = new LinkedHashMap<>();
            for (String c : proyeccion) {
                fila.put(c.trim().toLowerCase(), p.getCampo(c));
            }
            filas.add(fila);
        }
        return filas;
    }

    // ----------------- utilidades privadas -----------------

    private void validarCampo(String campo) {
        if (!CAMPOS.contains(campo.trim().toLowerCase())) {
            throw new IllegalArgumentException("Campo inexistente: " + campo);
        }
    }

    private int comparar(Object x, Object y) {
        if (x instanceof Number && y instanceof Number) {
            return Double.compare(((Number) x).doubleValue(), ((Number) y).doubleValue());
        }
        return x.toString().compareToIgnoreCase(y.toString());
    }

    private boolean cumple(Producto p, String campo, String operador, String valor) {
        Object v = p.getCampo(campo);
        int cmp;
        if (v instanceof Number) {
            cmp = Double.compare(((Number) v).doubleValue(), Double.parseDouble(valor.trim()));
        } else {
            if (operador.equalsIgnoreCase("LIKE")) {
                return v.toString().toLowerCase().contains(valor.trim().toLowerCase());
            }
            cmp = v.toString().compareToIgnoreCase(valor.trim());
        }
        switch (operador.trim().toUpperCase()) {
            case "=":  return cmp == 0;
            case "!=": return cmp != 0;
            case ">":  return cmp > 0;
            case "<":  return cmp < 0;
            case ">=": return cmp >= 0;
            case "<=": return cmp <= 0;
            default: throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }
}
