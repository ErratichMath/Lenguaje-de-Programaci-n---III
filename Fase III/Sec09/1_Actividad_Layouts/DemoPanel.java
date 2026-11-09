// DemoPanel.java - Prueba de MarcoPanel
import javax.swing.JFrame;

public class DemoPanel
{
   public static void main(String[] args)
   {
      MarcoPanel marcoPanel = new MarcoPanel();
      marcoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      marcoPanel.setSize(750, 200);
      marcoPanel.setVisible(true);
   }
}
