package View;

import Model.Viaje;
import View.UI.Componentes;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ViajePanel extends JPanel {
    public Viaje viaje;
    public JLabel origenLabel = new JLabel();
    public JLabel destinoLabel = new JLabel();
    public JLabel vehiculoLabel = new JLabel();
    public JLabel vechiculoImg = new JLabel();
    public JLabel recorridoLabel = new JLabel();
    public JLabel distanciaLabel = new JLabel();
    public JLabel gasolinaCurrent = new JLabel();

    public JPanel panelFin = new JPanel();
    public JPanel panelRecorrido = new JPanel();
    public JPanel panelInicio = new JPanel();
    public JPanel vehiculoPanel = new JPanel();
    public JButton iniciarBtn = new JButton("Iniciar");
    public JButton regresarBtn = new JButton("Regresar");
    public JButton gasolinaBtn = new JButton("Cargar Gas");
    public Componentes componentes = new Componentes();
    public double gasolina = 0;
    public boolean isRunning = true;
    public Thread hiloInicio;
    public Thread hiloRegresar;
    public Object lock = new Object();
    public boolean isRegresando = false;
    public ViajePanel(Viaje viaje) {
        this.viaje = viaje;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(componentes.paleta.FONDO);
        origenLabel.setText("Origen: " + viaje.getOrigen());
        destinoLabel.setText("Destino: " + viaje.getDestino());
        vehiculoLabel.setText(viaje.getNombreVehiculo());
        distanciaLabel.setText("Distancia: " + viaje.getDistancia() + " km");
        recorridoLabel.setText("Recorrido: " + viaje.getRecorridoKm() + " km");
        gasolina = 2;
        gasolinaCurrent.setText("Gasolina: " + gasolina + " L");

        origenLabel.setFont(new Font("Arial", Font.BOLD, 12));
        destinoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        vehiculoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        distanciaLabel.setFont(new Font("Arial", Font.BOLD, 12));
        recorridoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gasolinaCurrent.setFont(new Font("Arial", Font.BOLD, 12));

        origenLabel.setForeground(componentes.paleta.BLANCO);
        destinoLabel.setForeground(componentes.paleta.BLANCO);
        vehiculoLabel.setForeground(componentes.paleta.BLANCO);
        distanciaLabel.setForeground(componentes.paleta.BLANCO);
        recorridoLabel.setForeground(componentes.paleta.BLANCO);
        gasolinaCurrent.setForeground(componentes.paleta.BLANCO);

        componentes.primaryBtn(iniciarBtn);
        componentes.secondBtn(regresarBtn);
        componentes.secondBtn(gasolinaBtn);

        // gasolinaBtn permanece deshabilitado hasta que el vehiculo se quede sin gasolina
        gasolinaBtn.setEnabled(false);
        gasolinaBtn.setBackground(componentes.paleta.FONDO);

        iniciarBtn.addActionListener(this::onBtnInicio);
        regresarBtn.addActionListener(this::onBtnRegresar);


        // ver que tipo de transporte es para poner la imagen
        double gastoPorKm = viaje.getTransporte().getGastoPorKm();
        String tipoVehiculo = "";

        switch (Double.toString(gastoPorKm)) {
            case "0.45":
                tipoVehiculo = "vehiculoPremium";
                break;
            case "0.3":
                tipoVehiculo = "vehiculoNormal";
                break;
            case "0.1":
                tipoVehiculo = "motocicleta";
                break;
            default:
                tipoVehiculo = "vehiculoNormal";
                break;
        }

        String pathImg = "src/Assets/" + tipoVehiculo + ".png";

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(pathImg));
            Image nuevaImagen = bufferedImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(nuevaImagen);
            vechiculoImg.setIcon(imageIcon);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            vechiculoImg.setText("Imagen");
        }

        vechiculoImg.setPreferredSize(new Dimension(128, 128));

        panelFin.setLayout(new GridLayout(5,1, 10, 10));
        panelInicio.setLayout(new GridLayout(4, 1, 10, 10));
        panelRecorrido.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        vehiculoPanel.setLayout(new GridLayout(2, 1, 10, 10));

        panelFin.setBackground(componentes.paleta.FONDO);
        panelRecorrido.setBackground(componentes.paleta.FONDO);
        panelInicio.setBackground(componentes.paleta.FONDO);
        vehiculoPanel.setBackground(componentes.paleta.FONDO);
        setBackground(componentes.paleta.FONDO);

        panelRecorrido.setPreferredSize(new Dimension(670, 150));
        panelFin.setPreferredSize(new Dimension(150, 150));
        panelInicio.setPreferredSize(new Dimension(120, 100));
        vehiculoPanel.setPreferredSize(new Dimension(150, 110));

        gasolinaCurrent.setBorder(new EmptyBorder(0, 0, 0, 0));
        gasolinaCurrent.setPreferredSize(new Dimension(150, 40));
        gasolinaCurrent.setHorizontalAlignment(SwingConstants.CENTER);

        vehiculoPanel.add(gasolinaCurrent);
        vehiculoPanel.add(vechiculoImg);

        panelFin.add(vehiculoLabel);
        panelFin.add(distanciaLabel);
        panelFin.add(destinoLabel);
        panelFin.add(recorridoLabel);

        panelRecorrido.add(vehiculoPanel);

        panelInicio.add(origenLabel);
        panelInicio.add(iniciarBtn);
        panelInicio.add(regresarBtn);
        panelInicio.add(gasolinaBtn);

        gasolinaBtn.addActionListener(e -> {
            if (isRegresando) {
                hiloRegresar.interrupt();
            } else {
                hiloInicio.interrupt();
            }

            gasolina += viaje.getTransporte().getCapacidadTanque();

            gasolinaBtn.setEnabled(false);
            gasolinaBtn.setBackground(componentes.paleta.FONDO);
            gasolinaCurrent.setForeground(componentes.paleta.BLANCO);
            synchronized(lock) {
                lock.notify();
            }

        });

        add(panelFin);
        add(panelRecorrido);
        add(panelInicio);
    }

    public void onBtnInicio(ActionEvent e) {
        if (vehiculoPanel.getBounds().x == 520 && vehiculoPanel.getBounds().y == 10) {
            hiloIniciar();
            hiloRecorridoInicio();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "No se puede regresar, no te encuentras en la posicion final!"
            );
        }
    }

    public void isGasolineEmpty() {
        if (gasolina <= 0) {
            synchronized(lock) {
                try {
                    gasolinaCurrent.setText("Sin gasolina!");
                    gasolinaCurrent.setForeground(componentes.paleta.ROJO);

                    // habilitar el boton de gasolina
                    gasolinaBtn.setEnabled(true);
                    gasolinaBtn.setBackground(componentes.paleta.ROJO);
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public double currentPositionInit = 520;
    public void hiloIniciar() {
        double tiempo = (viaje.getDistancia() * 1000) / 260;
        hiloInicio = new Thread(() -> {

            for (double i = currentPositionInit; i >= 10; i -= 2) {
                currentPositionInit = i;
                vehiculoPanel.setBounds((int) i, 10, 128, 128);

                isGasolineEmpty();
                try {
                    Thread.sleep((int) tiempo);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });
        hiloInicio.start();
    }

    public void hiloRecorridoInicio() {
        Thread hiloRecorrido = new Thread(() -> {
            for (int i = 1; i <= viaje.getDistancia(); i++) {
                viaje.setRecorridoKm(i);
                recorridoLabel.setText("Recorrido: " + viaje.getRecorridoKm() + " km");
                // gasolina
                gasolina -= viaje.getTransporte().getGastoPorKm();
                // redoundear a 2 decimales
                gasolina = Math.round(gasolina * 100.0) / 100.0;

                isGasolineEmpty();


                gasolinaCurrent.setText("Gasolina: " + gasolina + " L");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            recorridoLabel.repaint();
        });
        hiloRecorrido.start();
    }

    public double currentPositionFinal = 10;
    public void hiloRegresar() {
        System.out.println(currentPositionInit);
        double tiempo = (viaje.getDistancia() * 1000) / 260;
        hiloRegresar = new Thread(() -> {
            for (double i = currentPositionInit; i <= 520; i += 2) {
                currentPositionInit = i;
                vehiculoPanel.setBounds((int) i, 10, 128, 128);

                isGasolineEmpty();

                try {
                    Thread.sleep((int) tiempo);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });
        hiloRegresar.start();
    }

    public void hiloRecorridoFin() {
        Thread hiloRecorrido = new Thread(() -> {
            for (int i = (int) viaje.getDistancia(); i >= 1; i--) {
                viaje.setRecorridoKm(viaje.getRecorridoKm() + 1);
                recorridoLabel.setText("Recorrido: " + viaje.getRecorridoKm() + " km");
                // gasolina
                gasolina -= viaje.getTransporte().getGastoPorKm();
                // redoundear a 2 decimales
                gasolina = Math.round(gasolina * 100.0) / 100.0;

                isGasolineEmpty();
                gasolinaCurrent.setText("Gasolina: " + gasolina + " L");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            recorridoLabel.repaint();
        });
        hiloRecorrido.start();
    }
    public void onBtnRegresar(ActionEvent e) {
        System.out.println(vehiculoPanel.getBounds().x);
        if (vehiculoPanel.getBounds().x == 10 && vehiculoPanel.getBounds().y == 10) {
            isRegresando = true;
            hiloRegresar();
            hiloRecorridoFin();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "No se puede regresar, no te encuentras en la posicion final!"
            );
        }
    }
}
