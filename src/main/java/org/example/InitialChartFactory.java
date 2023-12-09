package org.example;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class InitialChartFactory implements ChartFactory {
  /**
   * Creates an empty chart based on the provided DemographicData.
   *
   * @param data The DemographicData object containing the income distribution data.
   * @return An empty chart.
   */
  @Override
  public JFreeChart createChart(DemographicData data) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    return org.jfree.chart.ChartFactory.createBarChart(
        "No Data Available", // Chart title
        "", // Domain axis label
        "", // Range axis label
        dataset, // Data
        PlotOrientation.VERTICAL,
        false, // Include legend
        true, // Tooltips
        false // URLs
        );
  }
}
