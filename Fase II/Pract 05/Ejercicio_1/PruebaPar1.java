/**
 * EJERCICIO 1 - Prueba de la clase Par (getters, setters y toString).
 */
public class PruebaPar1 {
    public static void main(String[] args) {
        Par<String, Integer> par = new Par<>("Ana", 25);
        System.out.println("Par inicial: " + par);

        par.setPrimero("Luis");
        par.setSegundo(30);
        System.out.println("Par modificado: " + par);
        System.out.println("getPrimero(): " + par.getPrimero());
        System.out.println("getSegundo(): " + par.getSegundo());
    }
}
