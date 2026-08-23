package ACT02;

public class ContadorTest {
    public static void main(String[] args) {
        Contador c1, c2, c3;

        System.out.println("Acumulador inicial: " + Contador.acumulador());

        c1 = new Contador(3);
        c2 = new Contador(10);
        c1.inc();
        c1.inc();
        c2.inc();

        System.out.println("Valor c1: " + c1.getValor());        // 5
        System.out.println("Valor c2: " + c2.getValor());        // 11
        System.out.println("Acumulador: " + Contador.acumulador); // 16

        // g) Probando el constructor por defecto -> usa VALOR_INICIAL (10)
        System.out.println("\n--- Creando c3 con el constructor por defecto ---");
        c3 = new Contador();
        System.out.println("Valor c3: " + c3.getValor());         // 10
        System.out.println("Acumulador tras crear c3: " + Contador.acumulador); // 26

        // j.3) Probando nContadores y ultimoContador
        System.out.println("\n--- Estadísticas de clase ---");
        System.out.println("Número de contadores creados: " + Contador.getNContadores());   // 3
        System.out.println("Valor inicial del último contador creado: " + Contador.getUltimoContador()); // 10
    }
}