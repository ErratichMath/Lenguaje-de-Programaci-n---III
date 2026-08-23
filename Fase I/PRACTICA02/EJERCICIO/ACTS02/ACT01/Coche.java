package ACT01;

public class Coche {

    //Atributos
    public String color;
    private String modelo;
    private int velocidadMaxima;
    private int potenciaMotor;
    private boolean enMarcha;
    private String marca;
    private int anoFabricacion;
    private double precio;

    //Constructores

    // Constructor original (marca, velocidadMaxima, potenciaMotor)
    public Coche(String modelo, int velocidadMaxima, int potenciaMotor) {
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
        this.potenciaMotor = potenciaMotor;
        this.enMarcha = false;
    }

    // Segundo constructor: por defecto
    public Coche() {
        this.color = "Sin definir";
        this.modelo = "Sin definir";
        this.velocidadMaxima = 0;
        this.potenciaMotor = 0;
        this.enMarcha = false;
        this.marca = "Sin definir";
        this.anoFabricacion = 0;
        this.precio = 0.0;
    }

    // Tercer constructor: marca, modelo, añoFabricacion, precio
    public Coche(String marca, String modelo, int anoFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
        this.precio = precio;
        this.enMarcha = false;
    }

    // ===== Métodos =====

    public void acelerar() {
        if (enMarcha) {
            System.out.println("El coche " + modelo + " está acelerando.");
        } else {
            System.out.println("Primero enciende el coche.");
        }
    }

    public void frenar() {
        if (enMarcha) {
            System.out.println("El coche " + modelo + " está frenando.");
        } else {
            System.out.println("El coche está apagado, no se puede frenar.");
        }
    }

    public void encender() {
        enMarcha = true;
        System.out.println("El coche " + modelo + " se ha encendido.");
    }

    public void apagar() {
        enMarcha = false;
        System.out.println("El coche " + modelo + " se ha apagado.");
    }

    /**
     * Aplica un descuento al precio si el coche es un modelo antiguo
     * (fabricado antes del 2010).
     * @param descuento porcentaje de descuento a aplicar (ej. 10 = 10%)
     * @return true si se aplicó el descuento, false si no
     */
    public boolean aplicarDescuento(double descuento) {
        if (anoFabricacion < 2010) {
            precio = precio - (precio * descuento / 100.0);
            return true;
        }
        return false;
    }

    // ===== Getters y Setters =====

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(int potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public boolean isEnMarcha() {
        return enMarcha;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}