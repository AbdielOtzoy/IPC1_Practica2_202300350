package Model;

public class Ruta {
    private int id;
    private String inicio;
    private String fin;
    private double distancia;

    public Ruta(int id, String inicio, String fin, double distancia) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.distancia = distancia;
    }

    public int getId() {
        return id;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String toString() {
        return "Ruta{" + "id=" + id + ", inicio=" + inicio + ", fin=" + fin + ", distancia=" + distancia + '}';
    }
}
