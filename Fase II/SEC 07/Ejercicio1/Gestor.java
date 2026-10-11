import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
