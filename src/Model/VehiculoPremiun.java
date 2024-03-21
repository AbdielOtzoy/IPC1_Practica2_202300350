package Model;

import java.io.Serializable;

public class VehiculoPremiun extends Transporte implements Serializable {
    public VehiculoPremiun(String nombre) {
        super(nombre);
        setGastoPorKm(0.45);
        setCapacidadTanque(12);
        setRecorridoKm(0);
    }
}
