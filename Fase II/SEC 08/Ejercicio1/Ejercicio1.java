import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * EJERCICIO 1: menú por consola con SQLite + JDBC.
 * Tabla producto(id, nombre, categoria, precio) -> 4 campos.
 * Las operaciones que modifican datos (ingresar, borrar, actualizar) se confirman
 * con COMMIT solo si el usuario ingresa la clave correcta; si no, se hace ROLLBACK.
 */
public class Ejercicio1 {

    private static final String URL = "jdbc:sqlite:tienda.db";
    private static final String CLAVE = "1234";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL)) {
            crearTabla(con);
            con.setAutoCommit(false); // transacciones manuales

            int op;
            do {
                menu();
                op = leerInt("Opción: ");
                try {
                    switch (op) {
                        case 1: ingresar(con); break;
                        case 2: borrar(con); break;
                        case 3: actualizar(con); break;
                        case 4: mostrar(con); break;
                        case 0: System.out.println("Hasta luego."); break;
                        default: System.out.println("Opción inválida.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error de BD: " + e.getMessage() + " -> ROLLBACK");
                    con.rollback();
                }
            } while (op != 0);

        } catch (SQLException e) {
            System.out.println("No se pudo usar la base de datos: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    private static void menu() {
        System.out.println("\n===== MENÚ PRODUCTOS =====");
        System.out.println("1. Ingresar producto");
        System.out.println("2. Borrar producto");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Mostrar productos");
        System.out.println("0. Salir");
    }

    private static void crearTabla(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS producto ("
                    + "id INTEGER PRIMARY KEY, nombre TEXT NOT NULL, "
                    + "categoria TEXT, precio REAL)");
        }
    }

    // ---------------------------------------------------------------
    // Operaciones que modifican datos (PreparedStatement + clave)
    // ---------------------------------------------------------------
    private static void ingresar(Connection con) throws SQLException {
        int id = leerInt("Id: ");
        String nombre = leerTexto("Nombre: ");
        String categoria = leerTexto("Categoría: ");
        double precio = leerDouble("Precio: ");

        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO producto(id, nombre, categoria, precio) VALUES (?,?,?,?)")) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, categoria);
            ps.setDouble(4, precio);
            System.out.println(ps.executeUpdate() + " registro(s) insertado(s) (pendiente de confirmar)");
        }
        confirmar(con);
    }

    private static void borrar(Connection con) throws SQLException {
        int id = leerInt("Id del producto a borrar: ");
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM producto WHERE id = ?")) {
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            if (n == 0) {
                System.out.println("No existe un producto con id " + id + ". No se realizó ningún cambio.");
                con.rollback();
                return;
            }
            System.out.println(n + " registro(s) borrado(s) (pendiente de confirmar)");
        }
        confirmar(con);
    }

    private static void actualizar(Connection con) throws SQLException {
        int id = leerInt("Id del producto a actualizar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String categoria = leerTexto("Nueva categoría: ");
        double precio = leerDouble("Nuevo precio: ");

        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE producto SET nombre = ?, categoria = ?, precio = ? WHERE id = ?")) {
            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setDouble(3, precio);
            ps.setInt(4, id);
            int n = ps.executeUpdate();
            if (n == 0) {
                System.out.println("No existe un producto con id " + id + ". No se realizó ningún cambio.");
                con.rollback();
                return;
            }
            System.out.println(n + " registro(s) actualizado(s) (pendiente de confirmar)");
        }
        confirmar(con);
    }

    /** Si la clave es correcta -> COMMIT; de lo contrario -> ROLLBACK. */
    private static void confirmar(Connection con) throws SQLException {
        String clave = leerTexto("Ingrese la clave para confirmar los cambios: ");
        if (CLAVE.equals(clave)) {
            con.commit();
            System.out.println("Clave correcta. Cambios confirmados (COMMIT).");
        } else {
            con.rollback();
            System.out.println("Clave incorrecta. Cambios revertidos (ROLLBACK).");
        }
    }

    // ---------------------------------------------------------------
    // Mostrar datos
    // ---------------------------------------------------------------
    private static void mostrar(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, nombre, categoria, precio FROM producto ORDER BY id")) {
            System.out.println("\nID | NOMBRE | CATEGORÍA | PRECIO");
            boolean hay = false;
            while (rs.next()) {
                hay = true;
                System.out.println(rs.getInt("id") + " | " + rs.getString("nombre") + " | "
                        + rs.getString("categoria") + " | " + rs.getDouble("precio"));
            }
            if (!hay) {
                System.out.println("(la tabla está vacía)");
            }
        }
        con.commit(); // termina la lectura y libera el bloqueo del archivo .db
    }

    // ---------------------------------------------------------------
    // Lectura segura por consola
    // ---------------------------------------------------------------
    private static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int leerInt(String msg) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(msg));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(msg));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido (use punto decimal).");
            }
        }
    }
}
