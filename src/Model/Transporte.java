package Model;

import java.io.Serializable;

public class Transporte implements Serializable {
    private double gastoPorKm;
    private double capacidadTanque;
    private int recorridoKm;
    private String nombre;

    public Transporte(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public double getGastoPorKm() {
        return gastoPorKm;
    }

    public void setGastoPorKm(double gastoPorKm) {
        this.gastoPorKm = gastoPorKm;
    }

    public double getCapacidadTanque() {
        return capacidadTanque;
    }

    public void setCapacidadTanque(double capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    public int getRecorridoKm() {
        return recorridoKm;
    }

    public void setRecorridoKm(int recorridoKm) {
        this.recorridoKm = recorridoKm;
    }

    public void cargarCombustible(double litros) {
        if (litros > 0) {
            capacidadTanque += litros;
        }
    }

    public void consumirCombustible(double km) {
        if (km > 0) {
            capacidadTanque -= km * gastoPorKm;
        }
    }

    public void viajar(double km) {
        if (km > 0) {
            consumirCombustible(km);
        }
    }


    @Override
    public String toString() {
        return "Transporte{" +
                "gastoPorKm=" + gastoPorKm +
                ", capacidadTanque=" + capacidadTanque +
                ", recorridoKm=" + recorridoKm +
                '}';
    }
}
