/** MODELO: base común de Jugador y Enemigo (nombre, salud, nivel). */
public abstract class Personaje {
    protected final String nombre;
    protected int salud;
    protected final int saludMaxima;
    protected final int nivel;

    public Personaje(String nombre, int salud, int nivel) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.nivel = nivel;
    }

    /** Resta salud (mínimo 0) y devuelve el daño realmente recibido. */
    public int recibirDano(int dano) {
        salud = Math.max(0, salud - dano);
        return dano;
    }

    public boolean estaVivo() { return salud > 0; }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getNivel() { return nivel; }
}
