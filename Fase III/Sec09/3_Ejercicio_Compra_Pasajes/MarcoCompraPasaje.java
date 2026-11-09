// MarcoCompraPasaje.java
// Ejercicio 2: ingreso de datos para la compra de pasajes de una empresa de transporte terrestre.
// Componentes: JLabel, JTextField, JButton, JCheckBox, JRadioButton (ButtonGroup),
// JComboBox y JList, organizados con JPanel y distintos administradores de esquemas.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class MarcoCompraPasaje extends JFrame
{
   // *** CAMBIAR por los nombres de los integrantes del grupo ***
   private static final String INTEGRANTES = "Vilca Aragon Alexander Alexis, Ayerve Arenas Nicolas Matheus";

   private static final String[] CIUDADES =
      {"Arequipa", "Lima", "Cusco", "Tacna", "Puno", "Moquegua", "Ica", "Juliaca"};
   private static final String[] SERVICIOS = {"Economico", "Standard", "VIP"};

   // Etiquetas
   private final JLabel etiquetaTitulo;
   private final JLabel etiquetaNombres;
   private final JLabel etiquetaDocumento;
   private final JLabel etiquetaFecha;
   private final JLabel etiquetaOrigen;
   private final JLabel etiquetaDestino;

   // Campos de texto
   private final JTextField campoNombres;
   private final JTextField campoDocumento;
   private final JTextField campoFecha;

   // Casillas de verificación (servicios opcionales)
   private final JCheckBox checkAudifonos;
   private final JCheckBox checkManta;
   private final JCheckBox checkRevistas;

   // Botones de opción (piso)
   private final JRadioButton radioPrimerPiso;
   private final JRadioButton radioSegundoPiso;
   private final ButtonGroup grupoPiso;

   // Cuadros combinados
   private final JComboBox<String> comboOrigen;
   private final JComboBox<String> comboDestino;

   // Lista (calidad de servicio)
   private final JList<String> listaServicio;

   // Botones de comando
   private final JButton botonResumen;
   private final JButton botonReiniciar;

   public MarcoCompraPasaje()
   {
      super("Compra de pasajes - " + INTEGRANTES);
      setLayout(new BorderLayout(10, 10));

      // ---------- NORTH: título ----------
      etiquetaTitulo = new JLabel("COMPRA DE PASAJES TERRESTRES", SwingConstants.CENTER);
      etiquetaTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
      etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      add(etiquetaTitulo, BorderLayout.NORTH);

      // ---------- WEST: datos del pasajero y ruta (GridLayout) ----------
      etiquetaNombres = new JLabel("Nombres y apellidos:");
      etiquetaDocumento = new JLabel("Documento de identidad:");
      etiquetaFecha = new JLabel("Fecha de viaje (dd/mm/aaaa):");
      etiquetaOrigen = new JLabel("Origen:");
      etiquetaDestino = new JLabel("Destino:");

      campoNombres = new JTextField(18);
      campoDocumento = new JTextField(18);
      campoFecha = new JTextField(18);

      comboOrigen = new JComboBox<String>(CIUDADES);
      comboDestino = new JComboBox<String>(CIUDADES);
      comboDestino.setSelectedIndex(1); // por defecto distinto del origen
      comboOrigen.setMaximumRowCount(5);
      comboDestino.setMaximumRowCount(5);

      JPanel panelDatos = new JPanel(new GridLayout(5, 2, 8, 12));
      panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del pasajero y ruta"));
      panelDatos.add(etiquetaNombres);   panelDatos.add(campoNombres);
      panelDatos.add(etiquetaDocumento); panelDatos.add(campoDocumento);
      panelDatos.add(etiquetaFecha);     panelDatos.add(campoFecha);
      panelDatos.add(etiquetaOrigen);    panelDatos.add(comboOrigen);
      panelDatos.add(etiquetaDestino);   panelDatos.add(comboDestino);

      JPanel contenedorOeste = new JPanel(new BorderLayout());
      contenedorOeste.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
      contenedorOeste.add(panelDatos, BorderLayout.NORTH);
      add(contenedorOeste, BorderLayout.WEST);

      // ---------- CENTER: opciones del viaje (BoxLayout vertical) ----------
      radioPrimerPiso = new JRadioButton("1er piso", true);
      radioSegundoPiso = new JRadioButton("2do piso", false);
      grupoPiso = new ButtonGroup();
      grupoPiso.add(radioPrimerPiso);
      grupoPiso.add(radioSegundoPiso);

      JPanel panelPiso = new JPanel(new FlowLayout(FlowLayout.LEFT));
      panelPiso.setBorder(BorderFactory.createTitledBorder("Piso del bus"));
      panelPiso.add(radioPrimerPiso);
      panelPiso.add(radioSegundoPiso);

      checkAudifonos = new JCheckBox("Audifonos");
      checkManta = new JCheckBox("Manta");
      checkRevistas = new JCheckBox("Revistas");

      JPanel panelExtras = new JPanel(new FlowLayout(FlowLayout.LEFT));
      panelExtras.setBorder(BorderFactory.createTitledBorder("Servicios opcionales"));
      panelExtras.add(checkAudifonos);
      panelExtras.add(checkManta);
      panelExtras.add(checkRevistas);

      listaServicio = new JList<String>(SERVICIOS);
      listaServicio.setVisibleRowCount(3);
      listaServicio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listaServicio.setSelectedIndex(0);

      JPanel panelCalidad = new JPanel(new BorderLayout());
      panelCalidad.setBorder(BorderFactory.createTitledBorder("Calidad de servicio"));
      panelCalidad.add(new JScrollPane(listaServicio), BorderLayout.CENTER);

      JPanel panelOpciones = new JPanel();
      panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
      panelOpciones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
      panelOpciones.add(panelPiso);
      panelOpciones.add(panelExtras);
      panelOpciones.add(panelCalidad);
      add(panelOpciones, BorderLayout.CENTER);

      // ---------- SOUTH: botones de comando ----------
      botonResumen = new JButton("Mostrar resumen");
      botonReiniciar = new JButton("Reiniciar");

      JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
      panelBotones.add(botonResumen);
      panelBotones.add(botonReiniciar);
      add(panelBotones, BorderLayout.SOUTH);

      // ---------- Manejo de eventos (clases internas anónimas) ----------
      botonResumen.addActionListener(
         new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
               mostrarResumen();
            }
         }
      );

      botonReiniciar.addActionListener(
         new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
               reiniciar();
            }
         }
      );
   }

   // Valida los datos y muestra el resumen en un cuadro de diálogo
   private void mostrarResumen()
   {
      String nombres = campoNombres.getText().trim();
      String documento = campoDocumento.getText().trim();
      String fecha = campoFecha.getText().trim();

      if (nombres.isEmpty() || documento.isEmpty() || fecha.isEmpty())
      {
         JOptionPane.showMessageDialog(this,
            "Complete nombres, documento de identidad y fecha de viaje.",
            "Datos incompletos", JOptionPane.WARNING_MESSAGE);
         return;
      }

      if (!documento.matches("\\d{8,12}"))
      {
         JOptionPane.showMessageDialog(this,
            "El documento debe contener solo digitos (8 a 12).",
            "Documento invalido", JOptionPane.ERROR_MESSAGE);
         return;
      }

      if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}"))
      {
         JOptionPane.showMessageDialog(this,
            "La fecha debe tener el formato dd/mm/aaaa.",
            "Fecha invalida", JOptionPane.ERROR_MESSAGE);
         return;
      }

      String origen = (String) comboOrigen.getSelectedItem();
      String destino = (String) comboDestino.getSelectedItem();
      if (origen.equals(destino))
      {
         JOptionPane.showMessageDialog(this,
            "El origen y el destino deben ser distintos.",
            "Ruta invalida", JOptionPane.ERROR_MESSAGE);
         return;
      }

      String piso = radioPrimerPiso.isSelected() ? "1er piso" : "2do piso";

      StringBuilder extras = new StringBuilder();
      if (checkAudifonos.isSelected()) extras.append("Audifonos, ");
      if (checkManta.isSelected())     extras.append("Manta, ");
      if (checkRevistas.isSelected())  extras.append("Revistas, ");
      String textoExtras = extras.length() == 0
         ? "Ninguno" : extras.substring(0, extras.length() - 2);

      String calidad = listaServicio.getSelectedValue();
      if (calidad == null) calidad = SERVICIOS[0];

      String resumen = String.format(
         "RESUMEN DEL PASAJE%n%n" +
         "Pasajero: %s%n" +
         "Documento: %s%n" +
         "Fecha de viaje: %s%n" +
         "Ruta: %s -> %s%n" +
         "Piso: %s%n" +
         "Calidad de servicio: %s%n" +
         "Servicios opcionales: %s",
         nombres, documento, fecha, origen, destino, piso, calidad, textoExtras);

      JOptionPane.showMessageDialog(this, resumen, "Resumen de compra",
         JOptionPane.INFORMATION_MESSAGE);
   }

   // Deja todos los componentes en su estado inicial
   private void reiniciar()
   {
      campoNombres.setText("");
      campoDocumento.setText("");
      campoFecha.setText("");
      comboOrigen.setSelectedIndex(0);
      comboDestino.setSelectedIndex(1);
      radioPrimerPiso.setSelected(true);
      checkAudifonos.setSelected(false);
      checkManta.setSelected(false);
      checkRevistas.setSelected(false);
      listaServicio.setSelectedIndex(0);
      campoNombres.requestFocus();
   }
}
