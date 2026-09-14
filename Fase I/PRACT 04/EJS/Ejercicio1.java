import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/*
 * EJERCICIO 1: Lector de caracteres con excepciones personalizadas
 * -----------------------------------------------------------------
 * Se definen 4 excepciones: VocalException, NumeroException,
 * BlancoException y SalidaException.
 *
 * Cada una guarda el caracter que la origino (util para el mensaje).
 *
 * NOTA / SUPUESTO: el enunciado no especifica cual es el "caracter
 * de salida". Se asume que es el punto '.' (constante CARACTER_SALIDA).
 * Si tu profesor pidio otro caracter (p.ej. '#' o 'Q'), solo cambia
 * esa constante.
 */

// ---------- Excepciones personalizadas ----------

class VocalException extends Exception {
    private char caracter;

    public VocalException(char caracter) {
        super("Se leyo una vocal: '" + caracter + "'");
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }
}

class NumeroException extends Exception {
    private char caracter;

    public NumeroException(char caracter) {
        super("Se leyo un numero: '" + caracter + "'");
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }
}

class BlancoException extends Exception {
    private char caracter;

    public BlancoException(char caracter) {
        super("Se leyo un espacio en blanco");
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }
}

class SalidaException extends Exception {
    private char caracter;

    public SalidaException(char caracter) {
        super("Se leyo el caracter de salida: '" + caracter + "'");
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }
}

// ---------- Clase provista en la guia ----------

class LeerEntrada {
    private Reader stream;

    /**
     * Constructor
     * @param fuente la fuente de datos
     */
    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }

    /**
     * Obtiene el siguiente caracter del teclado.
     * @return el caracter leido
     * @throws IOException
     */
    public char getChar() throws IOException {
        return (char) this.stream.read();
    }
}

// ---------- Clase que procesa los caracteres leidos ----------

class ProcesadorCaracteres {

    public static final char CARACTER_SALIDA = '.'; // ver NOTA arriba

    private LeerEntrada lector;
    private char ultimoCaracter; // aqui se "almacena" el caracter leido

    public ProcesadorCaracteres(LeerEntrada lector) {
        this.lector = lector;
    }

    /**
     * Lee un caracter y, segun su valor, lanza la excepcion que
     * corresponda. Guarda el caracter leido en el atributo
     * ultimoCaracter antes de decidir que excepcion lanzar.
     */
    public void procesar() throws VocalException, NumeroException,
            BlancoException, SalidaException, IOException {

        char c = lector.getChar();
        this.ultimoCaracter = c;

        if (c == CARACTER_SALIDA) {
            throw new SalidaException(c);
        } else if ("aeiouAEIOU".indexOf(c) != -1) {
            throw new VocalException(c);
        } else if (Character.isDigit(c)) {
            throw new NumeroException(c);
        } else if (c == ' ') {
            throw new BlancoException(c);
        }
        // cualquier otro caracter (consonante, salto de linea, etc.)
        // simplemente no lanza excepcion
    }

    public char getUltimoCaracter() {
        return ultimoCaracter;
    }
}

// ---------- Clase principal ----------

public class Ejercicio1 {

    public static void main(String[] args) {
        LeerEntrada lector = new LeerEntrada(System.in);
        ProcesadorCaracteres procesador = new ProcesadorCaracteres(lector);

        System.out.println("Escribe caracteres (el '" +
                ProcesadorCaracteres.CARACTER_SALIDA + "' termina el programa):");

        boolean salir = false;
        while (!salir) {
            try {
                procesador.procesar();
            } catch (VocalException e) {
                System.out.println("[VOCAL] " + e.getMessage());
            } catch (NumeroException e) {
                System.out.println("[NUMERO] " + e.getMessage());
            } catch (BlancoException e) {
                System.out.println("[BLANCO] " + e.getMessage());
            } catch (SalidaException e) {
                System.out.println("[SALIDA] " + e.getMessage());
                System.out.println("Terminando el programa...");
                salir = true;
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
                salir = true;
            }
        }
    }
}
