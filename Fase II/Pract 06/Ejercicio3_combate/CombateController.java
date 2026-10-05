import java.util.List;

/** CONTROLADOR: gestiona los turnos del combate entre el jugador y los enemigos. */
public class CombateController {
    private final CombateModel modelo;
    private final CombateView vista;

    public CombateController(CombateModel modelo, CombateView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        Jugador jugador = modelo.getJugador();
        boolean huyo = false;
        vista.mostrarMensaje("¡Comienza el combate!");

        while (jugador.estaVivo() && modelo.quedanEnemigos() && !huyo) {
            vista.mostrarEstado(jugador, modelo.getEnemigos(), modelo.getTurno());
            vista.mostrarMenuCombate();
            String opcion = vista.solicitar("Elige una acción: ");
            boolean turnoConsumido = false;

            switch (opcion) {
                case "1": turnoConsumido = atacar(); break;
                case "2": turnoConsumido = usarObjeto(); break;
                case "3": vista.mostrarInventario(jugador.getInventario().obtenerItems()); break;
                case "0": huyo = true; break;
                default: vista.mostrarMensaje("Opción no válida.");
            }

            // Solo atacar o usar objeto gastan el turno; luego responden los enemigos
            if (turnoConsumido && modelo.quedanEnemigos()) {
                turnoEnemigos();
                modelo.siguienteTurno();
            }
        }

        if (huyo) vista.mostrarMensaje("Huiste del combate.");
        else if (!jugador.estaVivo()) vista.mostrarMensaje("Has sido derrotado... GAME OVER.");
        else vista.mostrarMensaje("¡Victoria! Todos los enemigos fueron derrotados.");
        vista.cerrarScanner();
    }

    /** El jugador elige un enemigo vivo y lo ataca. */
    private boolean atacar() {
        List<Enemigo> vivos = modelo.getEnemigosVivos();
        vista.mostrarObjetivos(vivos);
        Integer indice = pedirIndice("Elige enemigo: ", vivos.size());
        if (indice == null) return false;

        Enemigo objetivo = vivos.get(indice);
        int dano = modelo.getJugador().atacar(objetivo);
        vista.mostrarMensaje(modelo.getJugador().getNombre() + " ataca a " + objetivo.getNombre()
                + " e inflige " + dano + " de daño.");
        if (!objetivo.estaVivo()) vista.mostrarMensaje(objetivo.getNombre() + " ha sido derrotado.");
        return true;
    }

    /** El jugador elige un ítem de su inventario (equipar arma o usar poción). */
    private boolean usarObjeto() {
        List<Item> items = modelo.getJugador().getInventario().obtenerItems();
        if (items.isEmpty()) {
            vista.mostrarMensaje("No tienes objetos.");
            return false;
        }
        vista.mostrarInventario(items);
        Integer indice = pedirIndice("Elige objeto: ", items.size());
        if (indice == null) return false;

        vista.mostrarMensaje(modelo.getJugador().usarObjeto(items.get(indice)));
        return true;
    }

    /** Cada enemigo vivo realiza una acción aleatoria. */
    private void turnoEnemigos() {
        for (Enemigo e : modelo.getEnemigosVivos()) {
            if (!modelo.getJugador().estaVivo()) break;
            vista.mostrarMensaje(e.actuar(modelo.getJugador()));
        }
    }

    /** Pide un número entre 1 y 'max'; devuelve el índice (base 0) o null si es inválido. */
    private Integer pedirIndice(String mensaje, int max) {
        try {
            int n = Integer.parseInt(vista.solicitar(mensaje));
            if (n >= 1 && n <= max) return n - 1;
        } catch (NumberFormatException e) {
            // se informa abajo
        }
        vista.mostrarMensaje("Selección inválida.");
        return null;
    }
}
