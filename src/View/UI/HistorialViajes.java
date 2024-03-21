package View.UI;

import Model.Viaje;
import Model.Viajes;
import View.GenerarViaje;
import View.InciarViajes;
import View.RutasView;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class HistorialViajes extends JFrame implements Serializable {
    public JTable tablaViajes = new JTable();
    public JScrollPane scrollPane = new JScrollPane(tablaViajes);
    JLabel tituloLabel = new JLabel("Historial de viajes");
    public JPanel panelNav = new JPanel();
    public JButton btnRutas = new JButton("Rutas");
    public JButton btnViajes = new JButton("Iniciar viajes");
    public JButton btnGenerarViaje = new JButton("Generar viaje");
    public Viajes viajes = Viajes.getInstance();
    public Componentes componentes = new Componentes();
    public Paleta paleta = new Paleta();
    public JPanel MainPanel = new JPanel();

    public HistorialViajes() {
        setContentPane(MainPanel);
        setTitle("Historial de viajes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        componentes.rutaBtn(btnRutas);
        componentes.rutaBtn(btnViajes);
        componentes.rutaBtn(btnGenerarViaje);
        componentes.title(tituloLabel);

        panelNav.setLayout(new GridLayout(1, 3,10,0));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        panelNav.add(btnRutas);
        panelNav.add(btnViajes);
        panelNav.add(btnGenerarViaje);

        btnRutas.addActionListener(this::actionPerformed);
        btnViajes.addActionListener(this::actionPerformed);
        btnGenerarViaje.addActionListener(this::actionPerformed);

        scrollPane.setPreferredSize(new Dimension(800, 400));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelNav.setPreferredSize(new Dimension(800, 30));

        MainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        MainPanel.setBackground(paleta.FONDO);
        panelNav.setBackground(paleta.FONDO);
        MainPanel.add(tituloLabel);
        MainPanel.add(scrollPane);
        MainPanel.add(panelNav);



        cargarTablaViajes();
        setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btnRutas) {
            new RutasView();
            dispose();
        } else if (e.getSource() == btnViajes) {
            new InciarViajes();
            dispose();
        } else if (e.getSource() == btnGenerarViaje) {
            new GenerarViaje();
            dispose();
        }
    }

    public void cargarTablaViajes() {
        List<Viaje> listaViajes = viajes.getViajes();
        String[] columnas = {"Origen", "Destino", "Distancia", "Vehículo"};
        String[][] datos = new String[listaViajes.size()][5];
        for (int i = 0; i < listaViajes.size(); i++) {
            datos[i][0] = listaViajes.get(i).getOrigen();
            datos[i][1] = listaViajes.get(i).getDestino();
            datos[i][2] = String.valueOf(listaViajes.get(i).getDistancia());
            datos[i][3] = listaViajes.get(i).getNombreVehiculo();
        }
        tablaViajes = new JTable(datos, columnas);
        scrollPane.setViewportView(tablaViajes);

    }
}
