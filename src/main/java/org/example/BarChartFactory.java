package org.example;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartFactory implements ChartFactory {
  /**
   * Creates a bar chart displaying the income distribution data.
   *
   * @param data the demographic data containing income distribution information
   * @return a bar chart representing the income distribution data
   */
  @Override
  public JFreeChart createChart(DemographicData data) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    data.getIncomeDistribution()
        .forEach((incomeRange, count) -> dataset.addValue(count, "Households", incomeRange));
    return org.jfree.chart.ChartFactory.createBarChart(
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
