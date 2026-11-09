// MarcoRastreadorRaton.java
// Manejo de eventos de ratón.
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MarcoRastreadorRaton extends JFrame
{
   private final JPanel panelRaton;
   private final JLabel barraEstado;

   public MarcoRastreadorRaton()
   {
      super("Demostracion de los eventos de raton");

      panelRaton = new JPanel();
      panelRaton.setBackground(Color.WHITE);
      add(panelRaton, BorderLayout.CENTER);

      barraEstado = new JLabel("Raton fuera de JPanel");
      add(barraEstado, BorderLayout.SOUTH);

      ManejadorRaton manejador = new ManejadorRaton();
      panelRaton.addMouseListener(manejador);
      panelRaton.addMouseMotionListener(manejador);
   }

   private class ManejadorRaton implements MouseListener, MouseMotionListener
   {
      @Override public void mouseClicked(MouseEvent e)
      { barraEstado.setText(String.format("Se hizo clic en [%d, %d]", e.getX(), e.getY())); }

      @Override public void mousePressed(MouseEvent e)
      { barraEstado.setText(String.format("Se oprimio en [%d, %d]", e.getX(), e.getY())); }

      @Override public void mouseReleased(MouseEvent e)
      { barraEstado.setText(String.format("Se solto en [%d, %d]", e.getX(), e.getY())); }

      @Override public void mouseEntered(MouseEvent e)
      {
         barraEstado.setText(String.format("Raton entro en [%d, %d]", e.getX(), e.getY()));
         panelRaton.setBackground(Color.GREEN);
      }

      @Override public void mouseExited(MouseEvent e)
      {
         barraEstado.setText("Raton fuera de JPanel");
         panelRaton.setBackground(Color.WHITE);
      }

      @Override public void mouseDragged(MouseEvent e)
      { barraEstado.setText(String.format("Se arrastro en [%d, %d]", e.getX(), e.getY())); }

      @Override public void mouseMoved(MouseEvent e)
      { barraEstado.setText(String.format("Se movio en [%d, %d]", e.getX(), e.getY())); }
   }
}
