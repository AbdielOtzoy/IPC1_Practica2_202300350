package Controller;

import Model.RecorridoInfo;
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

    public void recorridos(List<RecorridoInfo> recorridos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("recorridos.bin"))) {
            FileOutputStream fileOut = new FileOutputStream("recorridos.bin", true); // true para modo de apertura para agregar
            oos.writeObject(recorridos);
            oos.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<RecorridoInfo> obtenerRecorridos() {
        List<RecorridoInfo> recorridos = null;
        try {
            FileInputStream fileIn = new FileInputStream("recorridos.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            recorridos = (List<RecorridoInfo>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recorridos;
    }


}
