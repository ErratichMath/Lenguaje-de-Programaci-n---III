/**
 * Ejercicio Propuesto 4: ISP
 * En vez de una interfaz Imprimible con imprimir() y escanear(),
 * se separan en dos interfaces específicas.
 */
public class Ejercicio4_ISP {

    interface Imprimible {
        void imprimir();
    }

    interface Escaneable {
        void escanear();
    }

    // Ya no está obligada a implementar escanear()
    static class Impresora implements Imprimible {
        public void imprimir() {
            System.out.println("Imprimiendo documento...");
        }
    }

    static class ImpresoraMultifuncional implements Imprimible, Escaneable {
        public void imprimir() {
            System.out.println("Imprimiendo documento (multifuncional)...");
        }
        public void escanear() {
            System.out.println("Escaneando documento...");
        }
    }

    public static void main(String[] args) {
        Impresora impresora = new Impresora();
        impresora.imprimir();

        ImpresoraMultifuncional multi = new ImpresoraMultifuncional();
        multi.imprimir();
        multi.escanear();
    }
}
