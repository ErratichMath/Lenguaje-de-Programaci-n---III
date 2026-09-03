/**
 * Ejercicio Propuesto 2: OCP
 * Se añade Triangulo implementando la interfaz Forma, sin tocar
 * Forma, Circulo ni Rectangulo.
 */
public class Ejercicio2_OCP {

    interface Forma {
        void dibujar();
    }

    static class Circulo implements Forma {
        public void dibujar() { System.out.println("Dibujando un círculo."); }
    }

    static class Rectangulo implements Forma {
        public void dibujar() { System.out.println("Dibujando un rectángulo."); }
    }

    // Nueva forma añadida sin modificar código existente (cumple OCP)
    static class Triangulo implements Forma {
        public void dibujar() { System.out.println("Dibujando un triángulo."); }
    }

    static class LienzoDibujo {
        void dibujarFormas(Forma[] formas) {
            for (Forma f : formas) f.dibujar();
        }
    }

    public static void main(String[] args) {
        Forma[] formas = { new Circulo(), new Rectangulo(), new Triangulo() };
        new LienzoDibujo().dibujarFormas(formas);
    }
}
