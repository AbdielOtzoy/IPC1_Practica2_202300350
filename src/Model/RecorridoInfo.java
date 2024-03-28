package Model;

import java.io.Serializable;

public class RecorridoInfo implements Serializable {
    private int id;
    private double position;
    private double gasolina;
    private double recorridoKm;
    private boolean isRegresando;

    public RecorridoInfo(int id, double position, double gasolina, double recorridoKm, boolean isRegresando) {
        this.id = id;
        this.position = position;
        this.gasolina = gasolina;
        this.recorridoKm = recorridoKm;
        this.isRegresando = isRegresando;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getGasolina() {
        return gasolina;
    }

    public void setGasolina(double gasolina) {
        this.gasolina = gasolina;
    }

    public double getRecorridoKm() {
        return recorridoKm;
    }

    public void setRecorridoKm(double recorridoKm) {
        this.recorridoKm = recorridoKm;
    }

    public boolean getIsRegresando() {
        return isRegresando;
    }

    public void setIsRegresando(boolean isRegresando) {
        this.isRegresando = isRegresando;
    }

    @Override
    public String toString() {
        return "RecorridoInfo{" +
                "id=" + id +
                ", position=" + position +
                ", gasolina=" + gasolina +
                ", recorridoKm=" + recorridoKm +
                '}';
    }
}
