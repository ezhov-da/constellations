package ru.ezhov.constellations.view.coordinates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class CoordinatesPanel extends JPanel {

    public CoordinatesPanel() throws IOException {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        ConstellationCoordinatesCutPanel imagePanel = new ConstellationCoordinatesCutPanel(
                new URL("https://st2.depositphotos.com/1963585/10247/v/950/depositphotos_102475060-stock-illustration-stock-vector-icons-with-scorpio.jpg")
        );
        JPanel panelCont = new JPanel();
        panelCont.add(imagePanel);
        panelCont.setBounds(0, 0, 500, 500);
        panelCont.setLocation(0, 0);

        Dimension dimension = new Dimension(500, 500);
        imagePanel.setMinimumSize(dimension);
        imagePanel.setMaximumSize(dimension);
        imagePanel.setPreferredSize(dimension);
        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    textArea.append(String.format("X: %s Y: %s\n", e.getX(), e.getY()));
                });
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(panelCont);
        splitPane.setBottomComponent(panel);
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
    }


    public class ConstellationCoordinatesCutPanel extends JPanel {
        private BufferedImage image;
        private File file;
        private URL url;

        private Set<Coordinates> coordinatesSet = new HashSet<>();


        public ConstellationCoordinatesCutPanel(File file) throws IOException {
            this.file = file;
            init();
        }

        public ConstellationCoordinatesCutPanel(URL url) throws IOException {
            this.url = url;
            init();
        }

        private void init() throws IOException {
            this.setLayout(null);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (file == null) {
                image = ImageIO.read(url);
            } else {
                image = ImageIO.read(file);
            }

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Coordinates coordinates = new Coordinates();
                        coordinates.setLocation(
                                e.getX() - (coordinates.getWidth() / 2),
                                e.getY() - (coordinates.getHeight() / 2)
                        );
                        coordinatesSet.add(coordinates);

                        coordinates.addMouseListener(new MouseAdapter() {

                            @Override
                            public void mouseClicked(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                if (e.getButton() == MouseEvent.BUTTON3) {
                                    System.out.println("Сделать меню");
                                }
                            }
                        });

                        ConstellationCoordinatesCutPanel.this.add(coordinates);
                        ConstellationCoordinatesCutPanel.this.repaint();
                        System.out.println("compo: " + ConstellationCoordinatesCutPanel.this.getComponents().length);
                    }
                }
            });

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension dimension = getSize();
            Graphics2D g2D = (Graphics2D) g;

            double maxSize = dimension.getWidth() > dimension.getHeight() ? dimension.getWidth() : dimension.getHeight();

            int w = image.getWidth();
            int h = image.getHeight();

            int max = w > h ? w : h;

            double wv = (w == max) ? maxSize : w / (max / maxSize);
            double hv = (h == max) ? maxSize : h / (max / maxSize);

            Image scaled = image.getScaledInstance((int) wv, (int) hv, Image.SCALE_SMOOTH);
            g2D.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2D.drawImage(scaled, 0, 0, null);
        }
    }
}
