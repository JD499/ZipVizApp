package org.example;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartFactory implements ChartFactory {
  /**
   * Creates a pie chart based on the provided DemographicData object.
   *
   * @param data The DemographicData object containing the income distribution data. It must not be
   *     null.
   * @return A JFreeChart object representing the pie chart with the income distribution data.
   */
  @Override
  public JFreeChart createChart(DemographicData data) {
    DefaultPieDataset dataset = new DefaultPieDataset();
    data.getIncomeDistribution().forEach(dataset::setValue);
    return org.jfree.chart.ChartFactory.createPieChart(
        "Income Distribution", // Chart title
        dataset, // Data
        true, // Include legend
        true, // Tooltips
        false // URLs
        );
  }
}
