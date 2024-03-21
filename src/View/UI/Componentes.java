package View.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Componentes {

    public Paleta paleta = new Paleta();
    public void primaryBtn(JButton btn) {
        btn.setBackground(paleta.PRIMARIO);
        btn.setForeground(paleta.BLANCO);
        btn.setFont(new Font("Arial", Font.BOLD,14));
    }
    public void secondBtn(JButton btn) {
        btn.setBackground(paleta.SECUNDARIO);
        btn.setForeground(paleta.BLANCO);
        btn.setFont(new Font("Arial",Font.BOLD,14));

    }
    public void rutaBtn(JButton btn) {
        btn.setBackground(null);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(paleta.BLANCO);

    }

    public void title(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(paleta.BLANCO);
    }
}
