package org.example;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    public MainFrame() {
        initializeComponents();
        setTitle("ZipViz - Demographic Data Visualizer");
        setSize(800, 600); // Set your desired size
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
