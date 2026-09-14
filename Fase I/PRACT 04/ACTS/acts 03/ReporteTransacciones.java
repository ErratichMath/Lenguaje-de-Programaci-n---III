import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReporteTransacciones {

    public void generarReporte(CuentaBancaria cuenta, String rutaArchivo) throws IOException {
        if (cuenta.getHistorialTransacciones().isEmpty()) {
            throw new HistorialVacioException("La cuenta " + cuenta.getNumeroCuenta() + " no tiene transacciones registradas");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
            writer.println("Titular: " + cuenta.getTitular());
            writer.println("Saldo: " + cuenta.getSaldo());
            writer.println("Transacciones:");
            for (String t : cuenta.getHistorialTransacciones()) {
                writer.println("  - " + t);
            }
        }
    }

    public void leerReporte(String rutaArchivo) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}
