package org.example;

import org.jfree.chart.JFreeChart;

import javax.swing.*;

public class VisualizationPanel extends JPanel {
    private DemographicChartPanel demographicChartPanel;

    public VisualizationPanel() {
        setupUI();
    }

    private void setupUI() {
        demographicChartPanel = new DemographicChartPanel();
        add(demographicChartPanel);
    }

    public void displayChart(JFreeChart chart, DemographicData data) {
        demographicChartPanel.setChart(chart, data);
        demographicChartPanel.revalidate();
    }
}