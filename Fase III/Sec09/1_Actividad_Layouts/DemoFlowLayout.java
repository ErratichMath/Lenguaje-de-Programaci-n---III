// DemoFlowLayout.java - Prueba MarcoFlowLayout
import javax.swing.JFrame;

public class DemoFlowLayout
{
   public static void main(String[] args)
   {
      MarcoFlowLayout marcoFlowLayout = new MarcoFlowLayout();
      marcoFlowLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      marcoFlowLayout.setSize(450, 100); // ancho suficiente para ver el título
      marcoFlowLayout.setVisible(true);
   }
}
