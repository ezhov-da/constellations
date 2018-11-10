package ru.ezhov.constellations.view.coordinates;

import javax.swing.*;
import java.io.IOException;

public class CoordinatesPanelTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable ex) {
                //
            }

            try {
                CoordinatesPanel coordinatesPanel = new CoordinatesPanel();
                JFrame frame = new JFrame("_________");
                frame.add(coordinatesPanel);
                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

