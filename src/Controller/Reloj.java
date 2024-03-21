import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Reloj {
    private int position;

    public Reloj() {
        this.position = 1;
    }

    public void moveForward() {
        System.out.println("Car is moving forward...");
        while (position < 10) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (position == 5) {
                System.out.println("Car has reached position 5. Press 'R' to continue the journey.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String input = reader.readLine();
                    if (!input.equalsIgnoreCase("R")) {
                        System.out.println("Invalid input. Exiting the program.");
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            position++;
            System.out.println("Car is at position: " + position);
        }
        System.out.println("Car has reached position 10.");
    }

    public static void main(String[] args) {
        Reloj carSimulation = new Reloj();
        Thread carThread = new Thread(carSimulation::moveForward);
        carThread.start();
    }
}

