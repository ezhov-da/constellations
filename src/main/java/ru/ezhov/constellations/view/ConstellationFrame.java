package ru.ezhov.constellations.view;

import ru.ezhov.constellations.infrastructure.store.ConstellationsJsonStore;
import ru.ezhov.constellations.view.constellation.ConstellationsPanel;
import ru.ezhov.constellations.view.coordinates.CoordinatesPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ConstellationFrame extends JFrame {
    public ConstellationFrame() throws HeadlessException, IOException {
        this.setTitle("Созвездия");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Просмотр созвездий",
                new ConstellationsPanel(
                        new ConstellationsJsonStore(new File("constellations.json")
                        )
                )
        );
        tabbedPane.addTab("Получение координат",
                new CoordinatesPanel()
        );
        this.add(tabbedPane, BorderLayout.CENTER);
    }
}
