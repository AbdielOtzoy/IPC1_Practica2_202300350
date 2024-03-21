package Model;

import java.util.ArrayList;

public class Rutas {
    private static Rutas instance;
    private ArrayList<Ruta> rutas;

    private Rutas() {
        rutas = new ArrayList<>();
    }

    public static Rutas getInstance() {
        if (instance == null) {
            instance = new Rutas();
        }
        return instance;
    }

    public void addRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }

    public Ruta getRuta(int id) {
        for (Ruta ruta : rutas) {
            if (ruta.getId() == id) {
                return ruta;
            }
        }
        return null;
    }

    public Ruta getRuta(String origen, String destino) {
        for (Ruta ruta : rutas) {
            if (ruta.getInicio().equals(origen) && ruta.getFin().equals(destino) || ruta.getInicio().equals(destino) && ruta.getFin().equals(origen)){
                return ruta;
            }
        }
        return null;
    }

    // agregar a cada ruta un id unico
    public void addId() {
        int id = 1;
        for (Ruta ruta : rutas) {
            ruta.setId(id);
            id++;
        }
    }

    public void modificarDistancia(int id, double distancia) {
        for (Ruta ruta : rutas) {
            if (ruta.getId() == id) {
                ruta.setDistancia(distancia);
            }
        }
    }



}
