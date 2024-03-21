package Controller;

import javax.swing.*;

public class RecorridoController extends Thread {
    public void run(JLabel recorrido) {
        while (true) {
            try {
                Thread.sleep(1000);
                recorrido.setText("Recorrido: " + (Integer.parseInt(recorrido.getText().split(" ")[1]) + 1) + " km");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
