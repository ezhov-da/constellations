package ru.ezhov.constellations.view;

import ru.ezhov.constellations.domain.Constellation;
import ru.ezhov.constellations.domain.Constellations;
import ru.ezhov.constellations.infrastructure.store.ConstellationsStore;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;

public class ConstellationsPanel extends JPanel {
    private ConstellationsStore constellationsStore;
    private ConstellationsListPanel constellationsListPanel;
    private JSplitPane splitPane;

    public ConstellationsPanel(ConstellationsStore constellationsStore) throws IOException {
        this.constellationsStore = constellationsStore;
        this.setLayout(new BorderLayout());
        this.constellationsListPanel = new ConstellationsListPanel();
        splitPane = new JSplitPane();
        splitPane.setLeftComponent(constellationsListPanel);
        splitPane.setRightComponent(new JPanel());
        splitPane.setDividerLocation(0.3);
        splitPane.setResizeWeight(0.3);
        this.add(splitPane, BorderLayout.CENTER);
    }

    private class ConstellationsListPanel extends JPanel {
        private JList<Constellation> constellationList;

        public ConstellationsListPanel() throws IOException {
            this.setLayout(new BorderLayout());
            Constellations constellations = constellationsStore.getAll();
            Constellation[] constellationsArray = new Constellation[constellations.getConstellations().size()];
            this.constellationList = new JList<Constellation>(constellations.getConstellations().toArray(constellationsArray));
            this.add(new JScrollPane(this.constellationList), BorderLayout.CENTER);
            this.constellationList.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    label.setText(((Constellation) value).getName());
                    return label;
                }
            });
            this.constellationList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    Constellation constellation = ConstellationsListPanel.this.constellationList.getSelectedValue();
                    if (constellation != null) {
                        SwingUtilities.invokeLater(() -> {
                            ConstellationsPanel.this.splitPane.remove(2);
                            ConstellationsPanel.this.splitPane.setRightComponent(new ConstellationPanel(constellation));
                        });
                    }
                }
            });
        }
    }
}
