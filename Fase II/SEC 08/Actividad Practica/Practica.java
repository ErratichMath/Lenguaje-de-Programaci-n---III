import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * EXPERIENCIA DE PRÁCTICA - Sesión 08
 * Tabla emp(id, name, age) en SQLite.
 *  1) CRUD con Statement
 *  2) CRUD con PreparedStatement
 *  3) Manejo de transacciones (commit / rollback)
 */
public class Practica {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver. Agrega el .jar de sqlite-jdbc al proyecto.");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:sqlite:ejemplo.db")) {
            System.out.println("Se creó y/o abrió la base de datos.");

            parte1Statement(con);
            parte2PreparedStatement(con);
            parte3Transacciones(con);

        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // 1) Operaciones básicas con Statement
    // ---------------------------------------------------------------
    private static void parte1Statement(Connection con) throws SQLException {
        System.out.println("\n=== 1) CRUD con Statement ===");
        try (Statement st = con.createStatement()) {
            st.execute("DROP TABLE IF EXISTS emp"); // permite ejecutar el programa varias veces
            st.execute("CREATE TABLE emp (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)");
            System.out.println("Creación de tabla exitosa");

            // INSERTAR
            st.executeUpdate("INSERT INTO emp VALUES (1,'Carlos',11)");
            st.executeUpdate("INSERT INTO emp VALUES (2,'Bety',22)");
            System.out.println("Inserción exitosa");
            mostrar(con, "Después de insertar");

            // ACTUALIZAR
            int act = st.executeUpdate("UPDATE emp SET age = 30 WHERE id = 1");
            System.out.println(act + " registro(s) actualizado(s)");
            mostrar(con, "Después de actualizar");

            // BORRAR
            int bor = st.executeUpdate("DELETE FROM emp WHERE id = 2");
            System.out.println(bor + " registro(s) borrado(s)");
            mostrar(con, "Después de borrar");
        }
    }

    // ---------------------------------------------------------------
    // 2) PreparedStatement (consultas parametrizadas)
    // ---------------------------------------------------------------
    private static void parte2PreparedStatement(Connection con) throws SQLException {
        System.out.println("\n=== 2) CRUD con PreparedStatement ===");

        // INSERTAR con parámetros
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO emp VALUES (?,?,?)")) {
            ps.setInt(1, 5);
            ps.setString(2, "Laura");
            ps.setInt(3, 55);          // OJO: el índice 3 es age (la guía tenía setInt(1,55) por error)
            System.out.println(ps.executeUpdate() + " registro(s) insertado(s)");

            ps.setInt(1, 6);
            ps.setString(2, "Pedro");
            ps.setInt(3, 40);
            System.out.println(ps.executeUpdate() + " registro(s) insertado(s)");
        }
        mostrar(con, "Después de insertar con PreparedStatement");

        // ACTUALIZAR con parámetros
        try (PreparedStatement ps = con.prepareStatement("UPDATE emp SET name = ?, age = ? WHERE id = ?")) {
            ps.setString(1, "Laura Gómez");
            ps.setInt(2, 56);
            ps.setInt(3, 5);
            System.out.println(ps.executeUpdate() + " registro(s) actualizado(s)");
        }
        mostrar(con, "Después de actualizar con PreparedStatement");

        // BORRAR con parámetros
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM emp WHERE id = ?")) {
            ps.setInt(1, 6);
            System.out.println(ps.executeUpdate() + " registro(s) borrado(s)");
        }

        // CONSULTAR con parámetros
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM emp WHERE age > ?")) {
            ps.setInt(1, 20);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Empleados con age > 20:");
                while (rs.next()) {
                    System.out.println("  " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
                }
            }
        }
    }

    // ---------------------------------------------------------------
    // 3) Transacciones: setAutoCommit(false), commit y rollback
    // ---------------------------------------------------------------
    private static void parte3Transacciones(Connection con) throws SQLException {
        System.out.println("\n=== 3) Transacciones ===");
        con.setAutoCommit(false); // activa el manejo manual de transacciones
        try (Statement st = con.createStatement()) {

            // Transacción 1: se CONFIRMA
            st.executeUpdate("INSERT INTO emp VALUES (190,'jose',77)");
            st.executeUpdate("INSERT INTO emp VALUES (191,'diana',88)");
            con.commit();
            System.out.println("COMMIT: se confirmaron jose y diana");

            // Transacción 2: se REVIERTE
            st.executeUpdate("INSERT INTO emp VALUES (200,'andy',99)");
            con.rollback();
            System.out.println("ROLLBACK: andy NO debe aparecer");

            // Transacción 3: rollback automático ante un error (id duplicado)
            try {
                st.executeUpdate("INSERT INTO emp VALUES (210,'maria',20)");
                st.executeUpdate("INSERT INTO emp VALUES (190,'repetido',1)"); // viola PRIMARY KEY
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error (" + e.getMessage() + ") -> ROLLBACK, maria tampoco se guarda");
            }
        } finally {
            con.setAutoCommit(true);
        }
        mostrar(con, "Estado final de la tabla");
    }

    // ---------------------------------------------------------------
    private static void mostrar(Connection con, String titulo) throws SQLException {
        System.out.println("--- " + titulo + " ---");
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM emp ORDER BY id")) {
            while (rs.next()) {
                System.out.println("  " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
        }
    }
}
