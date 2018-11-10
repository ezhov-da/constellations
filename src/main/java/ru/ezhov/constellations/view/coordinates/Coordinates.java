package ru.ezhov.constellations.view.coordinates;

import ru.ezhov.constellations.view.util.MouseMoveComponentListener;

import javax.swing.*;
import java.awt.*;

public class Coordinates extends Component {

    public Coordinates() {
        this.setSize(50, 50);
        MouseMoveComponentListener mouseMoveComponentListener = new MouseMoveComponentListener(this);
        this.addMouseMotionListener(mouseMoveComponentListener);
        this.addMouseListener(mouseMoveComponentListener);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        Dimension dimension = getSize();
        g2d.fillOval(20, 20, 10, 10);

        g2d.drawString("Test", 14, 14);
    }
}
