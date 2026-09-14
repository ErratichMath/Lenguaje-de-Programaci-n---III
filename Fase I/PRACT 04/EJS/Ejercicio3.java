/*
 * EJERCICIO 3: Gestion de Numeros Negativos
 * -------------------------------------------
 * setValor() lanza IllegalArgumentException si el valor es negativo.
 */

class Numero {
    private double valor;

    public Numero() {
        this.valor = 0.0;
    }

    public Numero(double valor) {
        setValor(valor);
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException(
                    "El valor no puede ser negativo: " + valor);
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

public class Ejercicio3 {

    public static void main(String[] args) {
        double[] valoresDePrueba = {15.5, -3.2, 0, 42, -100};

        Numero numero = new Numero();

        for (double v : valoresDePrueba) {
            try {
                numero.setValor(v);
                System.out.println("Valor establecido correctamente: "
                        + numero.getValor());
            } catch (IllegalArgumentException e) {
                System.out.println("Error al establecer " + v + ": "
                        + e.getMessage());
            }
        }
    }
}
