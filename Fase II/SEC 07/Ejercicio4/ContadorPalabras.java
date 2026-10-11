import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContadorPalabras {

    public static void main(String[] args) {
        File archivo = elegirArchivo();
        if (archivo == null) {
            System.out.println("No se selecciono ningun archivo, el programa termina");
            return;
        }
        procesarArchivo(archivo);
    }

    private static File elegirArchivo() {
        JFileChooser selector = new JFileChooser();
        while (true) {
            int resultado = selector.showOpenDialog(null);
            if (resultado != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            File archivo = selector.getSelectedFile();
            if (archivo.exists() && archivo.isFile()) {
                return archivo;
            }
            JOptionPane.showMessageDialog(null, "El archivo no existe o no es valido, intenta de nuevo");
        }
    }

    private static void procesarArchivo(File archivo) {
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        Map<String, Integer> frecuencia = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();

                StringBuilder palabraActual = new StringBuilder();
                for (int i = 0; i <= linea.length(); i++) {
                    boolean esLetraODigito = i < linea.length() && Character.isLetterOrDigit(linea.charAt(i));
                    if (esLetraODigito) {
                        palabraActual.append(linea.charAt(i));
                    } else if (palabraActual.length() > 0) {
                        String palabra = palabraActual.toString().toLowerCase();
                        totalPalabras++;
                        frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
                        palabraActual.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        double promedioPorLinea = totalLineas == 0 ? 0 : (double) totalPalabras / totalLineas;

        System.out.println("Total de lineas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres: " + totalCaracteres);
        System.out.println("Promedio de palabras por linea: " + promedioPorLinea);

        mostrarPalabrasMasFrecuentes(frecuencia, 5);
    }

    private static void mostrarPalabrasMasFrecuentes(Map<String, Integer> frecuencia, int cantidad) {
        List<Map.Entry<String, Integer>> entradas = new ArrayList<>(frecuencia.entrySet());
        entradas.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Palabras mas frecuentes:");
        for (int i = 0; i < Math.min(cantidad, entradas.size()); i++) {
            Map.Entry<String, Integer> entrada = entradas.get(i);
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
}
