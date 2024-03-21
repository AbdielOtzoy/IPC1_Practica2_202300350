package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Transportes implements Serializable {
    private static Transportes instance;
    private ArrayList<Transporte> transportes;

    private Transportes() {
        transportes = new ArrayList<>();
    }

    public static Transportes getInstance() {
        if (instance == null) {
            instance = new Transportes();
        }
        return instance;
    }

    public void addTransporte(Transporte transporte) {
        transportes.add(transporte);
    }

    public ArrayList<Transporte> getTransportes() {
        return transportes;
    }

    public void setTransportes(ArrayList<Transporte> transportes) {
        this.transportes = transportes;
    }

    public Transporte getTransporte(String nombre) {
        for (Transporte transporte : transportes) {
            if (transporte.getNombre().equals(nombre)) {
                return transporte;
            }
        }
        return null;
    }

    public void inicializar() {
        addTransporte(new Motocicleta("Motocicleta 1"));
        addTransporte(new Motocicleta("Motocicleta 2"));
        addTransporte(new Motocicleta("Motocicleta 3"));
        addTransporte(new VehiculoEstandar("Vehiculo Estándar 1"));
        addTransporte(new VehiculoEstandar("Vehiculo Estándar 2"));
        addTransporte(new VehiculoEstandar("Vehiculo Estándar 3"));
        addTransporte(new VehiculoPremiun("Vehiculo Premiun 1"));
        addTransporte(new VehiculoPremiun("Vehiculo Premiun 2"));
        addTransporte(new VehiculoPremiun("Vehiculo Premiun 3"));
    }



}
