package Model;

import java.io.Serializable;

public class Viaje implements Serializable {
    private int id;
    private String fechaIncio;
    private String horaInicio;
    private String fechaFin;
    private String horaFin;
    private String origen;
    private String destino;
    private double distancia;
    private String nombreVehiculo;
    private double gasolina;
    private Transporte transporte;
    private boolean enCurso;
    private double recorridoKm;
    private int positionX;

    public Viaje(String origen, String destino, double distancia, String nombreVehiculo, Transporte transporte) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.nombreVehiculo = nombreVehiculo;
        this.gasolina = 0;
        this.transporte = transporte;
        this.enCurso = true;
        this.recorridoKm = 0;
        positionX = 520;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public double getRecorridoKm() {
        return recorridoKm;
    }

    public void setRecorridoKm(double recorridoKm) {
        this.recorridoKm = recorridoKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(String fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getNombreVehiculo() {
        return nombreVehiculo;
    }

    public void setNombreVehiculo(String nombreVehiculo) {
        this.nombreVehiculo = nombreVehiculo;
    }

    public double getGasolina() {
        return gasolina;
    }

    public void setGasolina(double gasolinaConsumida) {
        this.gasolina = gasolina;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public boolean getEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "id=" + id +
                ", fechaIncio='" + fechaIncio + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", distancia=" + distancia +
                ", nombreVehiculo='" + nombreVehiculo + '\'' +
                ", gasolinaConsumida=" + gasolina +
                ", transporte=" + transporte +
                ", enCurso=" + enCurso +
                '}';
    }
}
