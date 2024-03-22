import Controller.CsvController;
import Controller.Serializa;
import Model.*;
import View.RutasView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static CsvController csvController = new CsvController();
    public static Rutas rutas = Rutas.getInstance();
    public static Transportes transportes = Transportes.getInstance();
    public static Viajes viajes = Viajes.getInstance();
    public static Serializa serializa = new Serializa();

    public static void main(String[] args) {
        // leer el archivo de viajes.bin
        try {
            List<Viaje> viajesList = serializa.obtenerViajes();
            if (viajesList != null) {
                for (Viaje viaje : viajesList) {
                    viajes.addViaje(viaje);
                }
                viajes.addId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        transportes.inicializar();

        RutasView rutasView = new RutasView();


    }
}