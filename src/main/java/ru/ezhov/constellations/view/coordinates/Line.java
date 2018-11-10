package ru.ezhov.constellations.view.coordinates;

import ru.ezhov.constellations.view.util.MouseMoveComponentListener;

import java.awt.*;

public class Line extends Component {

    public Line() {
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
        g2d.drawLine(0, 0, dimension.width, dimension.height);
    }
}
