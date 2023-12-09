package org.example;

import javax.swing.JFrame;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    public ApplicationWindow() {
        initializeComponents();
        setTitle("ZipViz - Demographic Data Visualizer");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        DemographicChartPanel demographicChartPanel = new DemographicChartPanel();
        DataInputPanel dataInputPanel = new DataInputPanel(demographicChartPanel);

        // Add components to the frame
        add(dataInputPanel, BorderLayout.NORTH);
        add(demographicChartPanel, BorderLayout.CENTER);
    }


}
