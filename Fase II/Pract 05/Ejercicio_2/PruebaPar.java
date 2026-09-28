/**
 * EJERCICIO 2 - Prueba del método esIgual de Par.
 */
public class PruebaPar {
    public static void main(String[] args) {
        Par<String, Integer> par1 = new Par<>("Ana", 25);
        Par<String, Integer> par2 = new Par<>("Ana", 25);
        Par<String, Integer> par3 = new Par<>("Luis", 30);

        System.out.println("par1: " + par1);
        System.out.println("par2: " + par2);
        System.out.println("par3: " + par3);

        System.out.println("par1.esIgual(par2): " + par1.esIgual(par2)); // true
        System.out.println("par1.esIgual(par3): " + par1.esIgual(par3)); // false
    }
}
