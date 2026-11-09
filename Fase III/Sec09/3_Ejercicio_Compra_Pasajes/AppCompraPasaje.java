// AppCompraPasaje.java - Ejecuta MarcoCompraPasaje
import javax.swing.JFrame;

public class AppCompraPasaje
{
   public static void main(String[] args)
   {
      MarcoCompraPasaje marco = new MarcoCompraPasaje();
      marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      marco.setSize(720, 400);
      marco.setLocationRelativeTo(null); // centra la ventana
      marco.setVisible(true);
   }
}
