package Controller;

import Model.Viaje;

import java.io.*;
import java.util.List;

public class Serializa {
    public void viajes(List<Viaje> viajes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("viajes.bin"))) {
            FileOutputStream fileOut = new FileOutputStream("viajes.bin", true); // true para modo de apertura para agregar
            oos.writeObject(viajes);
            oos.close();
            fileOut.close();
            System.out.println("Viaje guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // metodo para obtener los viajes
    public List<Viaje> obtenerViajes() {
        List<Viaje> viajes = null;
        try {
            FileInputStream fileIn = new FileInputStream("viajes.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            viajes = (List<Viaje>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return viajes;
    }


}
