package Controller;

import java.io.*;
import java.util.ArrayList;

public class CsvController {

    private BufferedReader bufferReader;
    private String line;
    private String parts[] = null;
    public String[][] leerCsv(String path) {
        ArrayList<String> datos = new ArrayList<String>();
        try {

            bufferReader = new BufferedReader(new FileReader(path));
            while((line = bufferReader.readLine()) != null) {
                parts = line.split(",");
                datos.add(parts[0]);
                datos.add(parts[1]);
                datos.add(parts[2]);
                // System.out.println(parts[0] + " " + parts[1] + " " + parts[2]);
            }
            bufferReader.close();
            line = null;
            parts = null;
            // eliminar el encabezado
            datos.remove(0);
            datos.remove(0);
            datos.remove(0);
            String[][] datosArray = new String[datos.size()/3][3];
            int j = 0;
            for (int i = 0; i < datos.size(); i+=3) {
                datosArray[j][0] = datos.get(i);
                datosArray[j][1] = datos.get(i+1);
                datosArray[j][2] = datos.get(i+2);
                j++;
            }
            return datosArray;

        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        return null;

    }

    public void modificarDistanciaCsv(String inicio, double distancia) {
        String directorioProyecto = System.getProperty("user.dir") + "/src/datos.csv";
        ArrayList<String> datos = new ArrayList<String>();
        try {
            bufferReader = new BufferedReader(new FileReader(directorioProyecto));
            while((line = bufferReader.readLine()) != null) {
                parts = line.split(",");
                if (parts[0].equals(inicio)) {
                    parts[2] = String.valueOf(distancia);
                }
                datos.add(parts[0] + "," + parts[1] + "," + parts[2]);
            }
            bufferReader.close();
            line = null;
            parts = null;
            FileWriter fileWriter = new FileWriter(directorioProyecto);
            for (String dato : datos) {
                fileWriter.write(dato + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public static void main(String[] args) {
        CsvController csv = new CsvController();
        // obtener el path del archivo
        String directorioProyecto = "/src/datos.csv";

        String[][] datos = csv.leerCsv(directorioProyecto);


    }
}
