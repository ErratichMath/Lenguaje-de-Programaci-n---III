import java.util.Random;

/** MODELO: enemigo (nombre, salud, nivel, tipo) que realiza acciones aleatorias. */
public class Enemigo extends Personaje {
    private final String tipo;
    private boolean defendiendo = false;
    private final Random random = new Random();

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        super(nombre, salud, nivel);
        this.tipo = tipo;
    }

    /** Ataque normal: daño según el nivel con un pequeño valor aleatorio. */
    public int atacar(Jugador jugador) {
        int dano = 2 + nivel * 2 + random.nextInt(3);
        return jugador.recibirDano(dano);
    }

    /** Si está en guardia, recibe la mitad del daño en el siguiente golpe. */
    @Override
    public int recibirDano(int dano) {
        if (defendiendo) {
            dano = Math.max(1, dano / 2);
            defendiendo = false;
        }
        return super.recibirDano(dano);
    }

    /** Elige una acción al azar y devuelve el mensaje de lo ocurrido. */
    public String actuar(Jugador jugador) {
        int r = random.nextInt(100);
        if (r < 50) {
            int dano = atacar(jugador);
            return nombre + " ataca e inflige " + dano + " de daño.";
        } else if (r < 70) {
            int dano = jugador.recibirDano((2 + nivel * 2) * 2);
            return nombre + " lanza un golpe fuerte e inflige " + dano + " de daño.";
        } else if (r < 85) {
            defendiendo = true;
            return nombre + " se pone en guardia (recibirá menos daño).";
        } else {
            return nombre + " falla su ataque.";
        }
    }

    public String getTipo() { return tipo; }
}
