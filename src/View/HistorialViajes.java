package View;

import Model.Viaje;
import Model.Viajes;
import View.GenerarViaje;
import View.InciarViajes;
import View.RutasView;
import View.UI.Componentes;
import View.UI.Paleta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        String[] columnas = {"id","Fecha y Hora de inicio", "Fecha y Hora de Fin", "Distancia","Vehiculo", "Gasolina consumida"};
        String[][] datos = new String[listaViajes.size()][6];
        for (int i = 0; i < listaViajes.size(); i++) {
            datos[i][0] = String.valueOf(listaViajes.get(i).getId());
            datos[i][1] = listaViajes.get(i).getFechaIncio() + " " + listaViajes.get(i).getHoraInicio();
            if(listaViajes.get(i).getFechaFin() == null){
                datos[i][2] = "En curso";
            } else {
                datos[i][2] = listaViajes.get(i).getFechaFin() + " " + listaViajes.get(i).getHoraFin();
            }
            datos[i][3] = String.valueOf(listaViajes.get(i).getDistancia());
            datos[i][4] = listaViajes.get(i).getNombreVehiculo();
            double gasolinaConsumida = listaViajes.get(i).getTransporte().getGastoPorKm() * listaViajes.get(i).getDistancia();
            gasolinaConsumida = Math.round(gasolinaConsumida * 100.0) / 100.0;
            datos[i][5] = String.valueOf(gasolinaConsumida);
        }

        tablaViajes = new JTable(datos, columnas);
        // estilizar tabla
        tablaViajes.setRowHeight(30);
        tablaViajes.getTableHeader().setReorderingAllowed(false);
        tablaViajes.setGridColor(new java.awt.Color(0, 0, 0));
        tablaViajes.setBorder(new EmptyBorder(0,0,0,0));
        tablaViajes.setGridColor(paleta.PRIMARIO);
        // se asigna el modelo a la tabla
        tablaViajes.getTableHeader().setBackground(paleta.PRIMARIO);
        tablaViajes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tablaViajes.getTableHeader().setForeground(paleta.BLANCO);
        scrollPane.setViewportView(tablaViajes);

    }
}
