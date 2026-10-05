import java.util.ArrayList;
import java.util.List;

/** MODELO: estado del combate (jugador, enemigos y turno actual). */
public class CombateModel {
    private final Jugador jugador;
    private final List<Enemigo> enemigos;
    private int turno = 1;

    public CombateModel(Jugador jugador, List<Enemigo> enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
    }

    public List<Enemigo> getEnemigosVivos() {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) vivos.add(e);
        }
        return vivos;
    }

    public boolean quedanEnemigos() { return !getEnemigosVivos().isEmpty(); }

    public void siguienteTurno() { turno++; }

    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }
    public int getTurno() { return turno; }
}
