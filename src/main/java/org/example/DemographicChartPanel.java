package org.example;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.JPanel;
import java.awt.*;

public class DemographicChartPanel extends JPanel {
    private ChartPanel chartPanel;
    private JFreeChart currentChart;
    private DemographicData currentData;
    private ChartFactory chartFactory;

    public DemographicChartPanel() {
        chartFactory = new InitialChartFactory();
        DemographicData data = null;
        JFreeChart initialChart = chartFactory.createChart(data);
        chartPanel = new ChartPanel(initialChart);
        add(chartPanel);
    }

    public void setChart(JFreeChart chart, DemographicData data) {
        this.currentChart = chart;
        this.currentData = data;
        chartPanel.setChart(chart);
        chartPanel.revalidate(); // Refresh the panel to display the new chart
        chartPanel.repaint();
    }

    public JFreeChart getChart() {
        return this.currentChart;
    }

    public DemographicData getData() {
        return this.currentData;
    }
}