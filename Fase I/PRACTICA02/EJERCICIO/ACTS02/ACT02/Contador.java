package ACT02;

public class Contador {
    static int acumulador = 0;
    final static int VALOR_INICIAL = 10;

    // j.1 - variable de clase: número de contadores creados
    static int nContadores = 0;
    // j.2 - variable de clase: valor inicial del último contador creado
    static int ultimoContador;

    private int valor;

    public static int acumulador() {
        return acumulador;
    }

    public Contador(int valor) {
        this.valor = valor;
        acumulador += valor;

        // j.1 y j.2: actualizamos las variables de clase
        nContadores++;
        ultimoContador = valor;
    }

    // e) Segundo constructor: crea un contador con el valor inicial por defecto
    public Contador() {
        this(Contador.VALOR_INICIAL);
    }

    public void inc() {
        valor++;
        acumulador++;
    }

    public int getValor() {
        return this.valor;
    }

    public static int getNContadores() {
        return nContadores;
    }

    public static int getUltimoContador() {
        return ultimoContador;
    }
}