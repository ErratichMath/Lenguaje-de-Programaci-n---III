import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Gestor {
    private static final String ARCHIVO = "personajes.txt";
    private List<Personaje> personajes;

    public Gestor() {
        personajes = new ArrayList<>();
        cargar();
    }

    public boolean agregarPersonaje(Personaje personaje) {
        if (buscarPersonaje(personaje.getNombre()) != null) {
            return false;
        }
        personajes.add(personaje);
        guardar();
        return true;
    }

    public boolean modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            return false;
        }
        personaje.setVida(vida);
        personaje.setAtaque(ataque);
        personaje.setDefensa(defensa);
        personaje.setAlcance(alcance);
        guardar();
        return true;
    }

    public boolean eliminarPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            return false;
        }
        personajes.remove(personaje);
        guardar();
        return true;
    }

    public Personaje buscarPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados");
            return;
        }
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }

    public void filtrarPorAtributo(String atributo) {
        List<Personaje> ordenados = new ArrayList<>(personajes);
        switch (atributo) {
            case "vida":
                ordenados.sort(Comparator.comparingInt(Personaje::getVida).reversed());
                break;
            case "ataque":
                ordenados.sort(Comparator.comparingInt(Personaje::getAtaque).reversed());
                break;
            case "defensa":
                ordenados.sort(Comparator.comparingInt(Personaje::getDefensa).reversed());
                break;
            case "alcance":
                ordenados.sort(Comparator.comparingInt(Personaje::getAlcance).reversed());
                break;
            case "nivel":
                ordenados.sort(Comparator.comparingInt(Personaje::getNivel).reversed());
                break;
            default:
                System.out.println("Atributo no reconocido: " + atributo);
                return;
        }
        System.out.println("=== Personajes ordenados por " + atributo + " ===");
        for (Personaje p : ordenados) {
            System.out.println(p);
        }
    }

    public void cargarPersonajesAleatorios(int cantidad) {
        Random random = new Random();
        String[] nombresBase = {"Thorne", "Lyra", "Garrick", "Sera", "Dorian", "Nyx"};
        for (int i = 0; i < cantidad; i++) {
            String nombre = nombresBase[random.nextInt(nombresBase.length)] + random.nextInt(1000);
            int vida = 50 + random.nextInt(100);
            int ataque = 10 + random.nextInt(30);
            int defensa = 5 + random.nextInt(20);
            int alcance = 1 + random.nextInt(10);
            agregarPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance));
        }
    }

    public boolean actualizarAtributo(String nombre, String atributo, int nuevoValor) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null || nuevoValor <= 0) {
            return false;
        }
        switch (atributo) {
            case "vida":
                personaje.setVida(nuevoValor);
                break;
            case "ataque":
                personaje.setAtaque(nuevoValor);
                break;
            case "defensa":
                personaje.setDefensa(nuevoValor);
                break;
            case "alcance":
                personaje.setAlcance(nuevoValor);
                break;
            default:
                return false;
        }
        guardar();
        return true;
    }

    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para calcular estadisticas");
            return;
        }
        double vidaTotal = 0;
        double ataqueTotal = 0;
        double defensaTotal = 0;
        double alcanceTotal = 0;
        for (Personaje p : personajes) {
            vidaTotal += p.getVida();
            ataqueTotal += p.getAtaque();
            defensaTotal += p.getDefensa();
            alcanceTotal += p.getAlcance();
        }
        int cantidad = personajes.size();
        System.out.println("Total de personajes: " + cantidad);
        System.out.println("Vida promedio: " + (vidaTotal / cantidad));
        System.out.println("Ataque promedio: " + (ataqueTotal / cantidad));
        System.out.println("Defensa promedio: " + (defensaTotal / cantidad));
        System.out.println("Alcance promedio: " + (alcanceTotal / cantidad));
    }

    public void importarDesdeArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.out.println("El archivo a importar no existe: " + rutaArchivo);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.isBlank()) {
                    agregarPersonaje(Personaje.desdeLinea(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al importar el archivo: " + e.getMessage());
        }
    }

    public boolean subirNivelPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            return false;
        }
        personaje.subirNivel();
        guardar();
        return true;
    }

    private void guardar() {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            for (Personaje p : personajes) {
                writer.write(p.toLinea() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    private void cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.isBlank()) {
                    personajes.add(Personaje.desdeLinea(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
