package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Viajes implements Serializable {
    private static Viajes instance;
    private ArrayList<Viaje> viajes;

    private Viajes() {
        viajes = new ArrayList<>();
    }

    public static Viajes getInstance() {
        if (instance == null) {
            instance = new Viajes();
        }
        return instance;
    }

    public void addViaje(Viaje viaje) {
        viajes.add(viaje);
    }

    public ArrayList<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(ArrayList<Viaje> viajes) {
        this.viajes = viajes;
    }

    public Viaje getViaje(int id) {
        for (Viaje viaje : viajes) {
            if (viaje.getId() == id) {
                return viaje;
            }
        }
        return null;
    }

    public void addId() {
        int id = 1;
        for (Viaje viaje : viajes) {
            viaje.setId(id);
            id++;
        }
    }
}
