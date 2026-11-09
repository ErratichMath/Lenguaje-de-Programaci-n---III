// MarcoPanel.java
// Uso de un objeto JPanel para ayudar a distribuir los componentes.
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MarcoPanel extends JFrame
{
   private final JPanel panelBotones;
   private final JButton[] botones;

   public MarcoPanel()
   {
      super("JPanel - " + MarcoFlowLayout.INTEGRANTES);
      botones = new JButton[5];
      panelBotones = new JPanel();
      panelBotones.setLayout(new GridLayout(1, botones.length));

      for (int cuenta = 0; cuenta < botones.length; cuenta++)
      {
         botones[cuenta] = new JButton("Boton " + (cuenta + 1));
         panelBotones.add(botones[cuenta]);
      }

      add(panelBotones, BorderLayout.SOUTH);
   }
}
