package org.example;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.JPanel;


public class DemographicChartPanel extends JPanel {
    private ChartPanel chartPanel;
    private JFreeChart currentChart;
    private DemographicData currentData;
    private ChartFactoryProvider chartFactoryProvider;

    public DemographicChartPanel() {
        chartFactoryProvider = new ChartFactoryProvider();
        DemographicData data = null;
        JFreeChart initialChart = chartFactoryProvider.getInitialChartFactory().createChart(data);
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