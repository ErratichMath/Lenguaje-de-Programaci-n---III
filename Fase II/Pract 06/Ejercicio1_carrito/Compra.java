import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** MODELO: registro de una compra realizada (para el historial). */
public class Compra {
    private static int contador = 1;

    private final int id;
    private final String fecha;
    private final List<String> detalle;
    private final double subtotal, descuento, envio, total;

    public Compra(List<String> detalle, double subtotal, double descuento, double envio, double total) {
        this.id = contador++;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.detalle = detalle;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.envio = envio;
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra #").append(id).append(" - ").append(fecha).append("\n");
        for (String d : detalle) sb.append("  ").append(d).append("\n");
        sb.append(String.format("  Subtotal: S/ %.2f%n  Descuento: -S/ %.2f%n  Envío: S/ %.2f%n  TOTAL: S/ %.2f",
                subtotal, descuento, envio, total));
        return sb.toString();
    }
}
