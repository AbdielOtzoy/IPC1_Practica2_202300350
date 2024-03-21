package View;

import Model.Motocicleta;
import Model.VehiculoPremiun;
import Model.Viaje;
import Model.Viajes;
import View.UI.Componentes;
import View.UI.HistorialViajes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class InciarViajes extends JFrame {
    public JPanel MainPanel = new JPanel();
    public Viajes viajes = Viajes.getInstance();
    public ArrayList<Viaje> viajesList = viajes.getViajes();
    public ArrayList<JPanel> viajesPanel = new ArrayList<JPanel>();
    public JButton rutasBtn = new JButton("Rutas");
    public JButton historialViajesBtn = new JButton("Historial de Viajes");
    public JButton generarViajeBtn = new JButton("Generar Viaje");
    public Componentes componentes = new Componentes();
    public JPanel panelNav = new JPanel();


    public InciarViajes() {
        setContentPane(MainPanel);
        setTitle("Iniciar Viajes");
        setSize(1120, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        for (Viaje viaje : viajesList) {
            Viaje newViaje = new Viaje(viaje.getOrigen(), viaje.getDestino(), viaje.getDistancia(), viaje.getNombreVehiculo(), viaje.getTransporte());
            viajesPanel.add(new ViajePanel(newViaje));
        }
        for (JPanel panel : viajesPanel) {
            MainPanel.add(panel);
        }
        int dif = 3 - viajesPanel.size();
        for (int i = 0; i < dif; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(componentes.paleta.FONDO);
            MainPanel.add(panel);
        }

        setLayout(new GridLayout(1,1, 10, 10));
        MainPanel.setLayout(new GridLayout(4,1, 10, 10));
        panelNav.setLayout(new GridLayout(1,3, 10, 10));

        setBackground(componentes.paleta.FONDO);
        MainPanel.setBackground(componentes.paleta.FONDO);
        panelNav.setBackground(componentes.paleta.FONDO);

        MainPanel.setBorder(new EmptyBorder(30,50, 30, 50));
        panelNav.setBorder(new EmptyBorder(55,0, 55, 0));

        componentes.rutaBtn(rutasBtn);
        componentes.rutaBtn(historialViajesBtn);
        componentes.rutaBtn(generarViajeBtn);

        rutasBtn.addActionListener(this::actionPerformed);
        historialViajesBtn.addActionListener(this::actionPerformed);
        generarViajeBtn.addActionListener(this::actionPerformed);

        panelNav.add(rutasBtn);
        panelNav.add(historialViajesBtn);
        panelNav.add(generarViajeBtn);

        MainPanel.add(panelNav);
        setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == rutasBtn) {
            new RutasView();
            setVisible(false);
        }
        if (e.getSource() == historialViajesBtn) {
            new HistorialViajes();
            setVisible(false);
        }
        if (e.getSource() == generarViajeBtn) {
            new GenerarViaje();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        // inciar un vieje ejemplo y mostrarlo
        Viaje viaje = new Viaje("Casa", "Trabajo", 15, "vehiculoPremium", new VehiculoPremiun("vehiculoPremium"));
        Viajes viajes = Viajes.getInstance();
        viajes.addViaje(viaje);

        Viaje viaje2 = new Viaje("Casa", "Trabajo", 15, "motocicleta", new Motocicleta("motocicleta"));
        viajes.addViaje(viaje2);


        new InciarViajes();

    }
}
