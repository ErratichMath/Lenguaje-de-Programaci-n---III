// Fig. 12.20: PruebaBotonOpcion.java
import javax.swing.JFrame;

public class PruebaBotonOpcion
{
   public static void main(String[] args)
   {
      MarcoBotonOpcion marcoBotonOpcion = new MarcoBotonOpcion();
      marcoBotonOpcion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      marcoBotonOpcion.setSize(300, 100);
      marcoBotonOpcion.setVisible(true);
   }
}
