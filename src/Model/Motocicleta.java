package Model;

import java.io.Serializable;

public class Motocicleta extends Transporte implements Serializable {
    public Motocicleta(String nombre) {
        super(nombre);
        setGastoPorKm(0.1);
        setCapacidadTanque(6);
        setRecorridoKm(0);
    }
}
