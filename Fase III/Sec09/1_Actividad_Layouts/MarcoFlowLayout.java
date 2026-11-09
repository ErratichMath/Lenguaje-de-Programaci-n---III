// MarcoFlowLayout.java
// FlowLayout permite que los componentes fluyan a través de varias líneas.
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

public class MarcoFlowLayout extends JFrame
{
   // *** CAMBIAR por los nombres de los integrantes del grupo ***
   public static final String INTEGRANTES = "Integrante1, Integrante2, Integrante3";

   private final JButton botonJButtonIzquierda; // alineación a la izquierda
   private final JButton botonJButtonCentro;    // alineación al centro
   private final JButton botonJButtonDerecha;   // alineación a la derecha
   private final FlowLayout esquema;            // objeto esquema
   private final Container contenedor;          // contenedor para establecer el esquema

   public MarcoFlowLayout()
   {
      super("FlowLayout - " + INTEGRANTES);

      esquema = new FlowLayout();
      contenedor = getContentPane();
      setLayout(esquema);

      botonJButtonIzquierda = new JButton("Izquierda");
      add(botonJButtonIzquierda);
      botonJButtonIzquierda.addActionListener(
         new ActionListener() // clase interna anónima
         {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
               esquema.setAlignment(FlowLayout.LEFT);
               esquema.layoutContainer(contenedor); // realinea los componentes
            }
         }
      );

      botonJButtonCentro = new JButton("Centro");
      add(botonJButtonCentro);
      botonJButtonCentro.addActionListener(
         new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
               esquema.setAlignment(FlowLayout.CENTER);
               esquema.layoutContainer(contenedor);
            }
         }
      );

      botonJButtonDerecha = new JButton("Derecha");
      add(botonJButtonDerecha);
      botonJButtonDerecha.addActionListener(
         new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
               esquema.setAlignment(FlowLayout.RIGHT);
               esquema.layoutContainer(contenedor);
            }
         }
      );
   }
}
