package org.example;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartFactory implements ChartFactory {
  /**
   * Creates a line chart based on the provided DemographicData.
   *
   * @param data The DemographicData object containing the income distribution data.
   * @return A JFreeChart object representing the line chart with the income distribution data.
   */
  @Override
  public JFreeChart createChart(DemographicData data) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    data.incomeDistribution()
        .forEach((incomeRange, count) -> dataset.addValue(count, "Households", incomeRange));
    return org.jfree.chart.ChartFactory.createLineChart(
        "Income Distribution", // Chart title
        "Income Range", // Domain axis label
        "Number of Households", // Range axis label
        dataset, // Data
        PlotOrientation.VERTICAL,
        true, // Include legend
        true, // Tooltips
        false // URLs
        );
  }
}
