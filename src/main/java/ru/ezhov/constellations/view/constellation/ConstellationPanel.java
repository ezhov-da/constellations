package ru.ezhov.constellations.view.constellation;

import ru.ezhov.constellations.domain.Constellation;
import ru.ezhov.constellations.domain.Star;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ConstellationPanel extends JPanel {
    private Constellation constellation;

    private JLabel label;
    private ConstellationPainterPanel painterPanel;

    public ConstellationPanel(Constellation constellation) {
        this.constellation = constellation;
        this.setBackground(Color.BLACK);
        this.label = new JLabel(constellation.getName());
        this.label.setForeground(Color.white);
        this.painterPanel = new ConstellationPainterPanel();
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.NORTH);
        this.add(painterPanel, BorderLayout.CENTER);
    }

    private class ConstellationPainterPanel extends JPanel {
        public ConstellationPainterPanel() {
            this.setBackground(Color.BLACK);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.WHITE);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            rh.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2D.setRenderingHints(rh);

            constellation.getStars().forEach((k, v) -> {
                g2D.drawOval(v.getX(), v.getY(), v.getRadius() * 2, v.getRadius() * 2);
                String name = v.getName();
                if (name != null && !"".equals(name)) {
                    g2D.drawString(v.getName(), v.getX(), v.getY() + (v.getRadius() * 2) + 20);
                }
            });

            Map<String, List<String>> map = constellation.getStarsRelation();
            map.forEach((k, v) -> {
                Star from = constellation.getStar(k);
                v.forEach(t -> {
                    Star to = constellation.getStar(t);
                    g2D.drawLine(
                            from.getX() + from.getRadius(),
                            from.getY() + from.getRadius(),
                            to.getX() + to.getRadius(),
                            to.getY() + to.getRadius()
                    );
                });
            });
        }
    }
}
