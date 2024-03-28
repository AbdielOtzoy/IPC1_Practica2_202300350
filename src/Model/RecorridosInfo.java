package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class RecorridosInfo implements Serializable {
    private static RecorridosInfo instance;
    private ArrayList<RecorridoInfo> recorridos;

    private RecorridosInfo() {
        recorridos = new ArrayList<>();
    }

    public static RecorridosInfo getInstance() {
        if (instance == null) {
            instance = new RecorridosInfo();
        }
        return instance;
    }

    public void addRecorrido(RecorridoInfo recorrido) {
        recorridos.add(recorrido);
    }

    public void deleteRecorrido(int id) {
        for (RecorridoInfo recorrido : recorridos) {
            if (recorrido.getId() == id) {
                recorridos.remove(recorrido);
                break;
            }
        }
    }

    public ArrayList<RecorridoInfo> getRecorridos() {
        return recorridos;
    }

    public RecorridoInfo getRecorrido(int id) {
        for (RecorridoInfo recorrido : recorridos) {
            if (recorrido.getId() == id) {
                return recorrido;
            }
        }
        return null;
    }

}
