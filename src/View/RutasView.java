package View;

import Controller.CsvController;
import Model.Ruta;
import Model.Rutas;
import View.UI.Componentes;
import View.UI.Paleta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class RutasView extends JFrame {

    private JButton cargarRutasCsvButton;
    private JButton editarDistanciaButton;
    private JPanel panelBotones;
    private JPanel MainPanel;
    private JTable table1;
    private JButton generarViajeButton;
    private JButton historialViajesButton;
    private JButton iniciarViajesButton;
    private JPanel panelRutas;
    public Paleta paleta = new Paleta();
    public Componentes componentes = new Componentes();
    public CsvController csvController = new CsvController();
    public Rutas rutas = Rutas.getInstance();
    public RutasView() {
        setContentPane(MainPanel);
        setTitle("Rutas");
        setSize(1000, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        cargarTabla();

        MainPanel.setBackground(paleta.FONDO);
        panelBotones.setBackground(paleta.FONDO);
        panelRutas.setBackground(paleta.FONDO);

        componentes.primaryBtn(cargarRutasCsvButton);
        componentes.secondBtn(editarDistanciaButton);
        componentes.rutaBtn(generarViajeButton);
        componentes.rutaBtn(historialViajesButton);
        componentes.rutaBtn(iniciarViajesButton);

        generarViajeButton.addActionListener(this::actionPerformed);
        historialViajesButton.addActionListener(this::actionPerformed);
        iniciarViajesButton.addActionListener(this::actionPerformed);
        cargarRutasCsvButton.addActionListener(this::actionPerformed);
        editarDistanciaButton.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == generarViajeButton) {
            // mostrar ventana para generar viaje
            new GenerarViaje();
            setVisible(false);
        }
        if (e.getSource() == historialViajesButton) {
            new HistorialViajes();
            setVisible(false);
        }
        if (e.getSource() == iniciarViajesButton) {
            // mostrar ventana para iniciar viajes
            new InciarViajes();
            setVisible(false);
        }
        if (e.getSource() == cargarRutasCsvButton) {
            JFileChooser fileChooser = new JFileChooser();

            // obtener el directorio actual
            String documentsPath = System.getProperty("user.dir");

            File documentsDirectory = new File(documentsPath);;

            // Verificar si el directorio existe
            if (documentsDirectory.exists() && documentsDirectory.isDirectory()) {
                fileChooser.setCurrentDirectory(documentsDirectory);
            } else {
                // Si el directorio no existe, se usa el directorio por defecto del usuario
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            }

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "You selected: " + selectedFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "No file selected.");
            }

            // verificar que el archivo sea .csv
            if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".csv")) {
                String pathFile = fileChooser.getSelectedFile().getAbsolutePath();
                String[][] datos = csvController.leerCsv(pathFile);
                for(String[] dato : datos) {
                    Ruta ruta = new Ruta(1,dato[0],dato[1],Double.parseDouble(dato[2]));
                    rutas.addRuta(ruta);
                }
                rutas.addId();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "El archivo seleccionado no es un archivo .csv");
            }
        }
        if (e.getSource() == editarDistanciaButton) {
            // pedir id de la ruta
            String id = JOptionPane.showInputDialog("Ingrese el id de la ruta");
            Ruta r = rutas.getRuta(Integer.parseInt(id));
            if (r != null) {
                // pedir la nueva distancia
                double distancia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva distancia"));
                csvController.modificarDistanciaCsv(r.getInicio(), distancia);
                rutas.modificarDistancia(Integer.parseInt(id), distancia);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Ruta no encontrada");
            }
        }
    }

    // método para cargar la tabla con los datos de las rutas
    public void cargarTabla() {
        // encabezados de la tabla
        String[] columnas = {"Id","Incio", "Fin", "Distancia (km)"};
        // datos de la tabla
        String[][] datos = new String[rutas.getRutas().size()][4];
        // se recorren las rutas y se agregan los datos a la tabla
        for (int i = 0; i < rutas.getRutas().size(); i++) {
            datos[i][0] = String.valueOf(rutas.getRutas().get(i).getId());
            datos[i][1] = rutas.getRutas().get(i).getInicio();
            datos[i][2] = rutas.getRutas().get(i).getFin();
            datos[i][3] = String.valueOf(rutas.getRutas().get(i).getDistancia());
        }

        // se crea el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        // estilizar tabla
        table1.setRowHeight(30);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setGridColor(new java.awt.Color(0, 0, 0));
        table1.setBorder(new EmptyBorder(0,0,0,0));
        table1.setGridColor(paleta.PRIMARIO);
        // se asigna el modelo a la tabla
        table1.getTableHeader().setBackground(paleta.PRIMARIO);
        table1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        table1.getTableHeader().setForeground(paleta.BLANCO);
        table1.setModel(model);

    }

    public static void main(String[] args) {
        new RutasView();
    }


}
