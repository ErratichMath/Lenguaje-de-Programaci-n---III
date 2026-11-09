// Fig. 12.23: MarcoLista.java
// Objeto JList que muestra una lista de colores.
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class MarcoLista extends JFrame
{
   private final JList<String> listaJListColores;
   private static final String[] nombresColores = {"Negro", "Azul", "Cyan",
      "Gris oscuro", "Gris", "Verde", "Gris claro", "Magenta",
      "Naranja", "Rosa", "Rojo", "Blanco", "Amarillo"};
   private static final Color[] colores = {Color.BLACK, Color.BLUE,
      Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
      Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
      Color.RED, Color.WHITE, Color.YELLOW};

   public MarcoLista()
   {
      super("Prueba de JList");
      setLayout(new FlowLayout());

      listaJListColores = new JList<String>(nombresColores);
      listaJListColores.setVisibleRowCount(5); // muestra cinco filas a la vez

      // no permite selecciones múltiples
      listaJListColores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // agrega al marco un objeto JScrollPane que contiene a JList
      add(new JScrollPane(listaJListColores));

      listaJListColores.addListSelectionListener(
         new ListSelectionListener() // clase interna anónima
         {
            @Override
            public void valueChanged(ListSelectionEvent evento)
            {
               int indice = listaJListColores.getSelectedIndex();
               if (indice >= 0) // evita -1 si no hay selección
                  getContentPane().setBackground(colores[indice]);
            }
         }
      );
   }
}
