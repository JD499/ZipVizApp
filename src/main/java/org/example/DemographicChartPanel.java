package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; // Aliasing for clarity
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.JPanel;

public class DemographicChartPanel extends JPanel {
    private ChartPanel chartPanel;

    public DemographicChartPanel() {

        JFreeChart initialChart = org.example.ChartFactory.createEmptyChart();
        chartPanel = new ChartPanel(initialChart);
        add(chartPanel);
    }

    public void setChart(JFreeChart chart) {
        // Update the chart in the existing chart panel
        chartPanel.setChart(chart);
        chartPanel.revalidate(); // Refresh the panel to display the new chart
        chartPanel.repaint();
    }
}


