public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this(nombre, vida, ataque, defensa, alcance, 1);
    }

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0 || nivel <= 0) {
            throw new IllegalArgumentException("Los atributos deben ser mayores que cero");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getNivel() {
        return nivel;
    }

    public void subirNivel() {
        nivel++;
        vida += 10;
        ataque += 3;
        defensa += 2;
        alcance += 1;
    }

    public String toLinea() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance + "," + nivel;
    }

    public static Personaje desdeLinea(String linea) {
        String[] partes = linea.split(",");
        int nivel = partes.length > 5 ? Integer.parseInt(partes[5]) : 1;
        return new Personaje(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]),
                Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), nivel);
    }

    @Override
    public String toString() {
        return nombre + " - nivel: " + nivel + ", vida: " + vida + ", ataque: " + ataque
                + ", defensa: " + defensa + ", alcance: " + alcance;
    }
}
