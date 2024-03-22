package View;

import Controller.Serializa;
import Model.*;
import View.UI.Componentes;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class GenerarViaje extends JFrame {

    private JPanel MainPanel = new JPanel();
    private JButton gerenarViajeButton = new JButton("Generar Viaje");
    private JLabel origenLabel = new JLabel();
    private JLabel destinoLabel = new JLabel();
    private JLabel vehiculoLabel = new JLabel();
    private JComboBox<String> origenComboBox = new JComboBox<String>();
    private JComboBox<String> destinoComboBox = new JComboBox<String>();
    private JComboBox<String> vehiculoComboBox = new JComboBox<String>();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JButton rutasBtn = new JButton("Rutas");
    private JButton inciarViajeBtn = new JButton("Iniciar Viajes");
    private JButton historialViajesBtn = new JButton("Historial de Viajes");
    private JPanel panelNav = new JPanel();
    public Componentes componentes = new Componentes();
    public Rutas rutas = Rutas.getInstance();
    public Transportes transportes = Transportes.getInstance();
    public Viajes viajes = Viajes.getInstance();
    public Serializa serializa = new Serializa();

    public GenerarViaje() {
        setContentPane(MainPanel);
        setTitle("Generar Viaje");
        setSize(1000, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gerenarViajeButton.addActionListener(this::actionPerformed);
        rutasBtn.addActionListener(this::actionPerformed);
        inciarViajeBtn.addActionListener(this::actionPerformed);
        historialViajesBtn.addActionListener(this::actionPerformed);
        origenLabel.setText("Seleccionar punto incial:");
        destinoLabel.setText("Seleccionar punto final:");
        vehiculoLabel.setText("Seleccionar vehículo:");

        componentes.title(origenLabel);
        componentes.title(destinoLabel);
        componentes.title(vehiculoLabel);
        componentes.primaryBtn(gerenarViajeButton);
        componentes.rutaBtn(rutasBtn);
        componentes.rutaBtn(inciarViajeBtn);
        componentes.rutaBtn(historialViajesBtn);

        llenarComboBox();

        vehiculoComboBox.addItem("Vehículo 1");
        vehiculoComboBox.addItem("Vehículo 2");

        MainPanel.setBackground(componentes.paleta.FONDO);
        panel1.setBackground(componentes.paleta.FONDO);
        panel2.setBackground(componentes.paleta.FONDO);
        panel3.setBackground(componentes.paleta.FONDO);
        panelNav.setBackground(componentes.paleta.FONDO);

        MainPanel.setLayout(new GridLayout(4, 1,10,15));
        panel1.setLayout(new GridLayout(2, 2,10,10));
        panel2.setLayout(new GridLayout(2, 2));
        panel3.setLayout(new GridLayout(1, 1,10,10));
        panelNav.setLayout(new GridLayout(1, 3,10,10));

        panel3.setBorder(new EmptyBorder(20, 0, 20, 0));
        panelNav.setBorder(new EmptyBorder(30, 0, 40, 0));

        MainPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        panel1.add(origenLabel);
        panel1.add(destinoLabel);
        panel1.add(origenComboBox);
        panel1.add(destinoComboBox);
        panel2.add(vehiculoLabel);
        panel2.add(vehiculoComboBox);
        panel3.add(gerenarViajeButton);
        panelNav.add(rutasBtn);
        panelNav.add(inciarViajeBtn);
        panelNav.add(historialViajesBtn);

        isDisponibleGenerar();

        MainPanel.add(panel1);
        MainPanel.add(panel2);
        MainPanel.add(panel3);
        MainPanel.add(panelNav);
        setVisible(true);
    }

    public void llenarComboBox() {
        // llenar los comboBox con los datos de la base de datos
        ArrayList<Ruta> rutaList = rutas.getRutas();

        // un punto inicial puede ser final y viceversa
        for (Ruta r : rutaList) {
            origenComboBox.addItem(r.getInicio());
            origenComboBox.addItem(r.getFin());
        }
        // al seleccionar el origen se debe mostrar solamente la opción con la misma distancia
        // y borrar si se tiene seleccionado un destino
        origenComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String origen = (String) origenComboBox.getSelectedItem();
                for (Ruta r : rutaList) {
                    if (r.getInicio().equals(origen)) {
                        destinoComboBox.removeAllItems();
                        destinoComboBox.addItem(r.getFin());
                    } else if (r.getFin().equals(origen)) {
                        destinoComboBox.removeAllItems();
                        destinoComboBox.addItem(r.getInicio());
                    }
                }
            }
        });

        ArrayList<String> transportesList = new ArrayList<>();
        for (int i = 0; i < transportes.getTransportes().size(); i++) {
            transportesList.add(transportes.getTransportes().get(i).getNombre());
        }
        for (String t : transportesList) {
            vehiculoComboBox.addItem(t);
        }

    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == gerenarViajeButton) {
            // verificar que los campos no estén vacíos
            if (origenComboBox.getSelectedItem() == null || destinoComboBox.getSelectedItem() == null || vehiculoComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Todos los campos son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String origen = (String) origenComboBox.getSelectedItem();
                String destino = (String) destinoComboBox.getSelectedItem();
                String vehiculoNombre = (String) vehiculoComboBox.getSelectedItem();
                Transporte transporte = transportes.getTransporte(vehiculoNombre);
                // verificar que el origen y destino no sean iguales
                if (origen.equals(destino)) {
                    JOptionPane.showMessageDialog(null, "El origen y destino no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                     // obtener la informacion de la ruta
                    Ruta ruta = rutas.getRuta(origen, destino);
                    Viaje viaje = new Viaje(origen, destino, ruta.getDistancia(), vehiculoNombre, transporte);


                    viajes.addViaje(viaje);
                    viajes.addId();

                    serializa.viajes(viajes.getViajes());

                    JOptionPane.showMessageDialog(null, "Viaje generado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    reinit();
                }
            }
        }
        if (e.getSource() == rutasBtn) {
            new RutasView();
            dispose();
        }
        if (e.getSource() == inciarViajeBtn) {
            new InciarViajes();
            dispose();
        }
        if (e.getSource() == historialViajesBtn) {
            new HistorialViajes();
            dispose();
        }
    }

    public void reinit() {
        origenComboBox.removeAllItems();
        destinoComboBox.removeAllItems();
        vehiculoComboBox.removeAllItems();
        llenarComboBox();
        ArrayList<Viaje> viajeList = viajes.getViajes();
        // contar cuantos viajes no han finalizado
        isDisponibleGenerar();


    }

    public void isDisponibleGenerar() {
        ArrayList<Viaje> viajeList = viajes.getViajes();
        int count = 0;
        for (Viaje v : viajeList) {
            if(v.getFechaFin() == null) {
                count++;
            }
        }
        // si hay más de 3 viajes no finalizados se debe mostrar un mensaje
        if (count >= 3) {
            gerenarViajeButton.setBackground(componentes.paleta.ROJO);
            gerenarViajeButton.setText("Los tres pilotos ya están en viaje, espere a que terminen");
            gerenarViajeButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new GenerarViaje();
    }
}
