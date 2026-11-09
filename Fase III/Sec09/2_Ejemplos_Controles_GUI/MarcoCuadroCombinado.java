// Fig. 12.21: MarcoCuadroCombinado.java
// Objeto JComboBox que muestra una lista de nombres de imágenes.
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MarcoCuadroCombinado extends JFrame
{
   private final JComboBox<String> imagenesJComboBox; // contiene los nombres de los iconos
   private final JLabel etiqueta; // muestra el icono seleccionado

   private static final String nombres[] =
      {"insecto1.gif", "insecto2.gif", "insectviaje.gif", "insectanim.gif"};
   private final Icon[] iconos = {
      new ImageIcon(getClass().getResource(nombres[0])),
      new ImageIcon(getClass().getResource(nombres[1])),
      new ImageIcon(getClass().getResource(nombres[2])),
      new ImageIcon(getClass().getResource(nombres[3]))};

   public MarcoCuadroCombinado()
   {
      super("Prueba de JComboBox");
      setLayout(new FlowLayout());

      imagenesJComboBox = new JComboBox<String>(nombres);
      imagenesJComboBox.setMaximumRowCount(3); // muestra tres filas

      imagenesJComboBox.addItemListener(
         new ItemListener() // clase interna anónima
         {
            @Override
            public void itemStateChanged(ItemEvent evento)
            {
               // determina si está seleccionado el elemento
               if (evento.getStateChange() == ItemEvent.SELECTED)
                  etiqueta.setIcon(iconos[
                     imagenesJComboBox.getSelectedIndex()]);
            }
         }
      );

      add(imagenesJComboBox);
      etiqueta = new JLabel(iconos[0]);
      add(etiqueta);
   }
}
