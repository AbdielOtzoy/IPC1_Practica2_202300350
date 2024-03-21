import Controller.CsvController;
import Model.*;
import View.RutasView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static CsvController csvController = new CsvController();
    public static Rutas rutas = Rutas.getInstance();
    public static Transportes transportes = Transportes.getInstance();
    public static Viajes viajes = Viajes.getInstance();

    public static void main(String[] args) {
        // leer el archivo de viajes.bin
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("viajes.bin"))) {
            Viaje viaje = (Viaje) ois.readObject();
            viajes.addViaje(viaje);
            for(Viaje v : viajes.getViajes()){
                System.out.println(v.getOrigen());
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        transportes.inicializar();

        RutasView rutasView = new RutasView();


    }
}