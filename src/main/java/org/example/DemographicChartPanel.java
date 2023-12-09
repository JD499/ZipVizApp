package org.example;

import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class DemographicChartPanel extends JPanel {
  private final ChartPanel chartPanel;
  private JFreeChart currentChart;
  private DemographicData currentData;

  /**
   * The DemographicChartPanel class represents a JPanel that displays a chart representing demographic data.
   */
  public DemographicChartPanel() {
    ChartFactoryProvider chartFactoryProvider = new ChartFactoryProvider();
    JFreeChart initialChart = chartFactoryProvider.getInitialChartFactory().createChart(null);
    chartPanel = new ChartPanel(initialChart);
    add(chartPanel);
  }

  /**
   * Sets the chart to be displayed in the DemographicChartPanel.
   *
   * @param chart The JFreeChart object representing the chart to be displayed.
   * @param data The DemographicData object containing the data for the chart.
   */
  public void setChart(JFreeChart chart, DemographicData data) {
    this.currentChart = chart;
    this.currentData = data;
    chartPanel.setChart(chart);
    chartPanel.revalidate();
    chartPanel.repaint();
  }

  /**
   * Retrieves the current chart displayed in the DemographicChartPanel.
   *
   * @return The JFreeChart object representing the current chart.
   */
  public JFreeChart getChart() {
    return this.currentChart;
  }

  /**
   * Retrieves the current DemographicData.
   *
   * @return The current DemographicData object.
   */
  public DemographicData getData() {
    return this.currentData;
  }
}
