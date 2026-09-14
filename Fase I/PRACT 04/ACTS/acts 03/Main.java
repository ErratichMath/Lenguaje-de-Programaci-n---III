import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ReporteTransacciones reporte = new ReporteTransacciones();

        CuentaBancaria cuentaSinMovimientos = new CuentaBancaria("001", "Juan Perez", 100.0);
        CuentaBancaria cuentaConMovimientos = new CuentaBancaria("002", "Maria Lopez", 200.0);
        cuentaConMovimientos.depositar(50.0);
        cuentaConMovimientos.retirar(30.0);

        System.out.println("=== Prueba: reporte de cuenta sin transacciones ===");
        try {
            reporte.generarReporte(cuentaSinMovimientos, "reporte_001.txt");
        } catch (HistorialVacioException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: reporte de cuenta con transacciones ===");
        try {
            reporte.generarReporte(cuentaConMovimientos, "reporte_002.txt");
            System.out.println("Reporte generado correctamente en reporte_002.txt");
        } catch (HistorialVacioException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }

        System.out.println("\n=== Prueba: lectura de reporte generado ===");
        try {
            reporte.leerReporte("reporte_002.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: archivo no encontrado");
        }

        System.out.println("\n=== Prueba: lectura de archivo inexistente ===");
        try {
            reporte.leerReporte("archivo_que_no_existe.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
