package org.example;

import org.jfree.chart.JFreeChart;

public interface ChartFactory {
  /**
   * Creates a JFreeChart object based on the provided DemographicData.
   *
   * @param data The DemographicData object containing the income distribution data.
   * @return A JFreeChart object representing the chart with the income distribution data.
   */
  JFreeChart createChart(DemographicData data);
}
