package Model;

import java.io.Serializable;

public class VehiculoEstandar extends Transporte implements Serializable {
    public VehiculoEstandar(String nombre) {
        super(nombre);
        setGastoPorKm(0.3);
        setCapacidadTanque(10);
        setRecorridoKm(0);
    }
}
