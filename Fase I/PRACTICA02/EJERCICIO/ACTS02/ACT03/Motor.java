package ACT03;

public class Motor {
    private int numMotor;
    private int revolucionesPorMin;

    public Motor(int numMotor, int revPorMin) {
        this.numMotor = numMotor;
        this.revolucionesPorMin = revPorMin;
    }

    public int getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(int numMotor) {
        this.numMotor = numMotor;
    }

    public int getRevoluciones() {
        return revolucionesPorMin;
    }

    public void setRevoluciones(int rev) {
        this.revolucionesPorMin = rev;
    }

    @Override
    public String toString() {
        return "Motor N°" + numMotor + " (" + revolucionesPorMin + " RPM)";
    }
}