/**
 * ACTIVIDAD 3
 * Método genérico esIgualA que compara dos argumentos con equals.
 */
public class IgualGenerico {

    // método genérico esIgualA
    // OJO: si x es null, x.equals(y) lanza NullPointerException
    public static <T> boolean esIgualA(T x, T y) {
        return x.equals(y);
    }

    // Versión más segura frente a null (opcional)
    public static <T> boolean esIgualASeguro(T x, T y) {
        return java.util.Objects.equals(x, y);
    }

    public static void main(String[] args) {
        // tipos integrados (autoboxing de int)
        System.out.printf("esIgualA(7, 7): %b%n", esIgualA(7, 7));
        System.out.printf("esIgualA(7, 8): %b%n", esIgualA(7, 8));

        // Integer y String (tipos distintos)
        Integer entero = 5;
        String texto = "5";
        System.out.printf("esIgualA(Integer 5, String \"5\"): %b%n",
            esIgualA(entero, texto));

        // Object
        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.printf("esIgualA(obj1, obj2): %b%n", esIgualA(obj1, obj2));
        System.out.printf("esIgualA(obj1, obj1): %b%n", esIgualA(obj1, obj1));

        // null como primer argumento -> NullPointerException
        try {
            String s = null;
            System.out.printf("esIgualA(null, \"hola\"): %b%n", esIgualA(s, "hola"));
        } catch (NullPointerException e) {
            System.out.println("Se produjo NullPointerException al comparar null.equals(...)");
        }

        // versión segura con Objects.equals
        String nulo = null;
        System.out.printf("esIgualASeguro(null, \"hola\"): %b%n", esIgualASeguro(nulo, "hola"));
    }
}
